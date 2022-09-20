package com.example.domain.repository

import com.example.domain.model.Characters

interface CharacterRemoteRepository {

    suspend fun getCharacters(page: Int, species: String = "", searchBy: String = ""): Result<Characters>

}