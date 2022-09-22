package com.example.appforportfolio.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.domain.model.Character
import com.example.domain.usecase.GetCharactersUseCase
import com.example.kodetrainee.paging.CharacterPagingSource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class FeedViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private var species = ""
    private var searchBy = ""
    val characters = Pager(PagingConfig( pageSize = 20, enablePlaceholders = true, initialLoadSize = 20)) {
        CharacterPagingSource(getCharactersUseCase, species, searchBy)
    }.flow.cachedIn(viewModelScope)

    fun setSpecies(species: String) {
        this.species = species
    }

    fun setSearchBy(searchBy: String) {
        this.searchBy = searchBy
    }

    class Factory @Inject constructor(
        private val getCharactersUseCase: GetCharactersUseCase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == FeedViewModel::class.java)
            return FeedViewModel(getCharactersUseCase) as T
        }
    }
}