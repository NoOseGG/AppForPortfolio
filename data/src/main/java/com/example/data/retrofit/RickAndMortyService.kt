package com.example.data.retrofit

import com.example.domain.model.Characters
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Characters
}