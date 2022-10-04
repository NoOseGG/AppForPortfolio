package com.example.domain.repository

import com.example.domain.model.CharacterDetails
import com.example.domain.model.Character

interface CharacterLocaleRepository {

    suspend fun getCharacters(): Result<List<Character>>

    suspend fun getCharacter(id: Int): Result<CharacterDetails>

    suspend fun insertCharacter(characterDetails: CharacterDetails)

    suspend fun deleteCharacter(characterDetails: CharacterDetails)
}