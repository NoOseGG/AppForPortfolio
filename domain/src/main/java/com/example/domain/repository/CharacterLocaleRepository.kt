package com.example.domain.repository

import com.example.domain.model.CharacterDetails

interface CharacterLocaleRepository {

    suspend fun getCharacters(): Result<List<CharacterDetails>>

    suspend fun insertCharacter(characterDetails: CharacterDetails)

    suspend fun deleteCharacter(characterDetails: CharacterDetails)
}