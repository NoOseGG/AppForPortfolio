package com.example.data.retrofit

import com.example.domain.model.CharacterDetails
import com.example.domain.model.Characters
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("species") species: String,
        @Query("name") searchBy: String,
    ): Characters

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): CharacterDetails
}