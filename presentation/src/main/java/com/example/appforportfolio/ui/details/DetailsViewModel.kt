package com.example.appforportfolio.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CharacterDetails
import com.example.domain.usecase.DeleteCharacterLocaleUseCase
import com.example.domain.usecase.GetCharacterLocaleUseCase
import com.example.domain.usecase.GetCharacterRemoteUseCase
import com.example.domain.usecase.InsertCharacterLocaleUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailsViewModel @AssistedInject constructor(
    @Assisted private val characterId: Int,
    private val getCharacterRemoteUseCase: GetCharacterRemoteUseCase,
    private val getCharacterLocaleUseCase: GetCharacterLocaleUseCase,
    private val insertCharacterLocaleUseCase: InsertCharacterLocaleUseCase,
    private val deleteCharacterLocaleUseCase: DeleteCharacterLocaleUseCase
) : ViewModel() {

    val error = MutableSharedFlow<String>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    val characterDetails = MutableSharedFlow<CharacterDetails>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    private val favourite = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterLocaleUseCase(id = characterId).fold(onSuccess = { character ->
                characterDetails.tryEmit(character)
            }, onFailure = {
                getCharacterRemoteUseCase(characterId).fold(onSuccess = { character ->
                    characterDetails.tryEmit(character)
                }, onFailure = { errorMessage ->
                    error.tryEmit(errorMessage.message.toString())
                })
            })
        }

        favourite.map {
            characterDetails.filterIsInstance<CharacterDetails>().first()
        }.onEach { character ->
            if (character.isFavourites) {
                deleteCharacterLocaleUseCase(character)
            } else {
                insertCharacterLocaleUseCase(character.copy(isFavourites = true))
            }
            characterDetails.tryEmit(character.copy(isFavourites = !character.isFavourites))
        }.launchIn(viewModelScope)
    }

    fun onFavouriteClicked() {
        favourite.tryEmit(Unit)
    }

    @AssistedFactory
    interface Factory {
        fun create(characterId: Int): DetailsViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory, characterId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(characterId) as T
            }
        }
    }
}