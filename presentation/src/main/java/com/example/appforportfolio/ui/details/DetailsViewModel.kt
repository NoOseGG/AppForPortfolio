package com.example.appforportfolio.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.CharacterDetails
import com.example.domain.usecase.GetCharacterUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailsViewModel(
    private val characterId: Int,
    private val getCharacterUseCase: GetCharacterUseCase,
) : ViewModel() {

    val error = MutableSharedFlow<String>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    val characterDetails = flow<CharacterDetails> {
        getCharacterUseCase.getCharacter(characterId).fold(
            onSuccess = {
                emit(it)
            },
            onFailure = {
                error.tryEmit(it.message.toString())
            }
        )
    }


    class Factory(
        private val characterId: Int,
        private val getCharacterUseCase: GetCharacterUseCase
    ): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == DetailsViewModel::class.java)
            return DetailsViewModel(characterId, getCharacterUseCase) as T
        }

        class Factory @Inject constructor(
            val getCharacterUseCase: GetCharacterUseCase
        ) {

            fun create(characterId: Int): DetailsViewModel.Factory {
                return DetailsViewModel.Factory(characterId, getCharacterUseCase)
            }
        }
    }
}