package com.example.appforportfolio.ui.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.appforportfolio.MyApp
import com.example.appforportfolio.R
import com.example.appforportfolio.ServiceLocator
import com.example.appforportfolio.databinding.FragmentDetailsBinding
import com.example.appforportfolio.ui.BaseFragment
import com.example.domain.model.CharacterDetails
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(
    FragmentDetailsBinding::inflate
) {

    @Inject
    lateinit var factory: DetailsViewModel.Factory

    private val args: DetailsFragmentArgs by navArgs()
    private val detailsViewModel: DetailsViewModel by viewModels {
        DetailsViewModel.provideFactory(factory, args.characterId)
    }

    override fun onAttach(context: Context) {
        ServiceLocator.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnBackArrowListener()
        setObserveViewModel()
    }

    private fun setObserveViewModel() {
        detailsViewModel.characterDetails.onEach { characterDetails ->
            updateUi(characterDetails)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        detailsViewModel.error.onEach { message ->
            showError(message)
        }
    }

    private fun setOnBackArrowListener() {
        binding.imgArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun updateUi(characterDetails: CharacterDetails) {
        with(binding) {
            imgCharacterDetailsAvatar.load(characterDetails.image)
            tvCharacterDetailsName.text = characterDetails.name
            tvCharacterDetailsSpecies.text = characterDetails.species
            tvStatus.text = getString(R.string.fragment_details_status, characterDetails.status)
            tvType.text = getString(R.string.fragment_details_type, characterDetails.type)
            tvGender.text = getString(R.string.fragment_details_gender, characterDetails.gender)
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}