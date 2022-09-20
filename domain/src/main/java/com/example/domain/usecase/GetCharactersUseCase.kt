package com.example.domain.usecase

import com.example.domain.model.Characters
import com.example.domain.repository.CharacterRemoteRepository

class GetCharactersUseCase(
    private val characterRemoteRepository: CharacterRemoteRepository
) {

    suspend fun getCharacters(
        page: Int, species: String = "", searchBy: String = ""
    ): Result<Characters> {

        return characterRemoteRepository.getCharacters(page, species, searchBy)
    }
}