package com.example.domain.usecase

import com.example.domain.model.CharacterDetails
import com.example.domain.repository.CharacterRemoteRepository

class GetCharacterRemoteUseCase(
    private val characterRemoteRepository: CharacterRemoteRepository
) {

    suspend operator fun invoke(id: Int): Result<CharacterDetails> {
        return characterRemoteRepository.getCharacter(id)
    }
}