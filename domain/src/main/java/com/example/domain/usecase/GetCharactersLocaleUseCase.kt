package com.example.domain.usecase

import com.example.domain.model.Character
import com.example.domain.repository.CharacterLocaleRepository

class GetCharactersLocaleUseCase(

    private val characterLocaleRepository: CharacterLocaleRepository
) {

    suspend operator fun invoke(): Result<List<Character>> {
        return characterLocaleRepository.getCharacters()
    }
}