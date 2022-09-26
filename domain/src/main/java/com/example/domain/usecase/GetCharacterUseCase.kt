package com.example.domain.usecase

import com.example.domain.model.CharacterDetails
import com.example.domain.repository.CharacterRemoteRepository

class GetCharacterUseCase(
    private val characterRemoteRepository: CharacterRemoteRepository
) {

    suspend fun getCharacter(id: Int): Result<CharacterDetails> {
        return characterRemoteRepository.getCharacter(id)
    }
}