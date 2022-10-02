package com.example.domain.usecase

import com.example.domain.model.CharacterDetails
import com.example.domain.repository.CharacterLocaleRepository

class GetCharacterLocaleUseCase(
    private val characterLocaleRepository: CharacterLocaleRepository
) {

    suspend operator fun invoke(id: Int): Result<CharacterDetails> {
        return characterLocaleRepository.getCharacter(id)
    }
}