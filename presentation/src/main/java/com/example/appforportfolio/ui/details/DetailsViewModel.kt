package com.example.appforportfolio.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.CharacterDetails
import com.example.domain.usecase.GetCharacterUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class DetailsViewModel @AssistedInject constructor(
    @Assisted private val characterId: Int,
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

    @AssistedFactory
    interface Factory {
        fun create(characterId: Int): DetailsViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            characterId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(characterId) as T
            }
        }
    }
}