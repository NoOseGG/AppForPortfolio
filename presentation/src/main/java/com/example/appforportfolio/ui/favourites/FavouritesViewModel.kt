package com.example.appforportfolio.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.GetCharactersLocaleUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavouritesViewModel (
    private val getCharactersLocaleUseCase: GetCharactersLocaleUseCase
): ViewModel() {

    val error = MutableSharedFlow<String>()

    val characters = flow {
        getCharactersLocaleUseCase().fold(
            onSuccess = {
                emit(it)
            },
            onFailure = {
                error.tryEmit(it.message.toString())
            }
        )
    }

    class Factory @Inject constructor(
        private val getCharactersLocaleUseCase: GetCharactersLocaleUseCase
    ): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == FavouritesViewModel::class.java)
            return FavouritesViewModel(getCharactersLocaleUseCase) as T
        }
    }
}