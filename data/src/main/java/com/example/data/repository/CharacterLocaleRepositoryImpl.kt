package com.example.data.repository

import com.example.data.mapper.toDomain
import com.example.data.mapper.toEntity
import com.example.data.room.CharacterDao
import com.example.domain.model.CharacterDetails
import com.example.domain.repository.CharacterLocaleRepository
import javax.inject.Inject

class CharacterLocaleRepositoryImpl @Inject constructor(

    private val characterDao: CharacterDao
) : CharacterLocaleRepository {
    override suspend fun getCharacters(): Result<List<CharacterDetails>> {
        return kotlin.runCatching {
            emptyList<CharacterDetails>()
        }
    }

    override suspend fun getCharacter(id: Int): Result<CharacterDetails> {
        return runCatching {
            characterDao.getCharacter(id).toDomain()
        }
    }

    override suspend fun insertCharacter(characterDetails: CharacterDetails) {
        characterDao.insertCharacter(characterDetails.toEntity())
    }

    override suspend fun deleteCharacter(characterDetails: CharacterDetails) {
        characterDao.deleteCharacter(characterDetails.toEntity())
    }
}