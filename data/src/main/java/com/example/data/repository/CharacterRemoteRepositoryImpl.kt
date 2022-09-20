package com.example.data.repository

import com.example.data.retrofit.RickAndMortyService
import com.example.domain.model.Characters
import com.example.domain.repository.CharacterRemoteRepository

class CharacterRemoteRepositoryImpl(
    private val rickAndMortyService: RickAndMortyService
) : CharacterRemoteRepository {

    override suspend fun getCharacters(
        page: Int,
        species: String,
        searchBy: String
    ): Result<Characters> {

        return runCatching {
            rickAndMortyService.getCharacters(page, species, searchBy)
        }
    }
}