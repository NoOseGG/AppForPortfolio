package com.example.data.di

import com.example.domain.repository.CharacterRemoteRepository
import com.example.domain.usecase.GetCharactersUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetCharactersUseCase(
        characterRemoteRepository: CharacterRemoteRepository
    ): GetCharactersUseCase {

        return GetCharactersUseCase(characterRemoteRepository)
    }
}