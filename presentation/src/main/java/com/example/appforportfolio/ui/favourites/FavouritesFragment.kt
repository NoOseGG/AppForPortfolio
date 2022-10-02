package com.example.appforportfolio.ui.favourites

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appforportfolio.ServiceLocator
import com.example.appforportfolio.databinding.FragmentFavouritesBinding
import com.example.appforportfolio.ui.BaseFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>(
    FragmentFavouritesBinding::inflate
) {

    @Inject
    lateinit var favouritesViewModelFactory: dagger.Lazy<FavouritesViewModel.Factory>

    private val favouritesViewModel: FavouritesViewModel by viewModels {
        favouritesViewModelFactory.get()
    }
    private val characterAdapter by lazy {
        CharacterFavouritesAdapter() {

        }
    }

    override fun onAttach(context: Context) {
        val appComponent = ServiceLocator(requireContext()).appComponent
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialRecyclerView()

        favouritesViewModel.characters.onEach {
            characterAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        favouritesViewModel.error.onEach {

        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initialRecyclerView() {
        with(binding) {
            recyclerView.layoutManager = GridLayoutManager(
                requireContext(),
                SPAN_COUNT,
                GridLayoutManager.VERTICAL,
                false
            )
            recyclerView.adapter = characterAdapter
        }
    }

    companion object {
        private const val SPAN_COUNT = 2
    }
}