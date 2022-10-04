package com.example.domain.usecase

import com.example.domain.model.CharacterDetails
import com.example.domain.repository.CharacterLocaleRepository

class DeleteCharacterLocaleUseCase(

    private val characterLocaleRepository: CharacterLocaleRepository
) {

    suspend operator fun invoke(characterDetails: CharacterDetails) {
        characterLocaleRepository.deleteCharacter(characterDetails)
    }
}