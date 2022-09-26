package com.example.data.repository

import com.example.data.retrofit.RickAndMortyService
import com.example.domain.model.CharacterDetails
import com.example.domain.model.Characters
import com.example.domain.repository.CharacterRemoteRepository
import javax.inject.Inject

class CharacterRemoteRepositoryImpl @Inject constructor(
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

    override suspend fun getCharacter(id: Int): Result<CharacterDetails> {
        return runCatching {
            rickAndMortyService.getCharacter(id)
        }
    }

}