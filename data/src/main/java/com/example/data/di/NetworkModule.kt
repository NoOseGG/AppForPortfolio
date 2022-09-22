package com.example.data.di

import com.example.data.retrofit.RickAndMortyService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @Provides
    fun provideRickAndMortyService(
        okHttpClient: OkHttpClient
    ): RickAndMortyService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}
