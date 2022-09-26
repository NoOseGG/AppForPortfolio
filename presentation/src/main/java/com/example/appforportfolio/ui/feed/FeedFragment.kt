package com.example.appforportfolio.ui.feed

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appforportfolio.ServiceLocator
import com.example.appforportfolio.databinding.FragmentFeedBinding
import com.example.appforportfolio.ui.BaseFragment
import com.example.kodetrainee.adapter.CharacterAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FeedFragment : BaseFragment<FragmentFeedBinding>(FragmentFeedBinding::inflate) {

    @Inject
    lateinit var feedViewModelFactory: dagger.Lazy<FeedViewModel.Factory>

    private val feedViewModel: FeedViewModel by viewModels {
        feedViewModelFactory.get()
    }

    private val adapterCharacter by lazy {
        CharacterAdapter() { character ->
            val action = FeedFragmentDirections.actionFeedFragmentToDetailsFragment(character.id)
            findNavController().navigate(action)
        }
    }

    override fun onAttach(context: Context) {
        ServiceLocator.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialRecyclerView()
        setSearchViewListener()
        setSwipeRefreshListener()
        setTabLayoutListener()

        feedViewModel.characters.onEach { characters ->
            adapterCharacter.submitData(characters)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun initialRecyclerView() {
        with(binding) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.scrollToPosition(0)
            recyclerView.adapter = adapterCharacter
        }
    }

    private fun checkSpecies(species: Int) {
        when(species) {
            ALL -> showSpeciesCharacters(SPECIES_ALL)
            HUMAN -> showSpeciesCharacters(SPECIES_HUMAN)
            ALIEN -> showSpeciesCharacters(SPECIES_ALIEN)
            MYTHOLOGICAL -> showSpeciesCharacters(SPECIES_MYTHOLOGICAL)
            UNKNOWN -> showSpeciesCharacters(SPECIES_UNKNOWN)
        }
    }

    private fun showSpeciesCharacters(species: String) {
        feedViewModel.setSpecies(species)
        feedViewModel.setSearchBy(SPECIES_ALL)
        adapterCharacter.refresh()
    }

    private fun showSearchCharacters(species: String) {
        feedViewModel.setSpecies(SPECIES_ALL)
        adapterCharacter.refresh()
    }

    private fun setSearchViewListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(searchBy: String?): Boolean {
                feedViewModel.setSearchBy(searchBy.toString())
                showSearchCharacters(SPECIES_ALL)
                return false
            }
        })
    }

    private fun setTabLayoutListener() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab?.position != null) checkSpecies(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                println("${tab?.text.toString()} unselected")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if(tab?.position != null) checkSpecies(tab.position)
            }
        })
    }

    private fun setSwipeRefreshListener() {
        binding.swipeRefresh.setOnRefreshListener {
            showToast("Data refreshed")
            adapterCharacter.refresh()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val ALL = 0
        private const val HUMAN = 1
        private const val ALIEN = 2
        private const val MYTHOLOGICAL = 3
        private const val UNKNOWN = 4

        private const val SPECIES_ALL = ""
        private const val SPECIES_HUMAN = "Human"
        private const val SPECIES_ALIEN = "Alien"
        private const val SPECIES_MYTHOLOGICAL = "Mythological"
        private const val SPECIES_UNKNOWN = "Unknown"
    }
}