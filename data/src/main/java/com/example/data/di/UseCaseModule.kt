package com.example.data.di

import com.example.domain.repository.CharacterLocaleRepository
import com.example.domain.repository.CharacterRemoteRepository
import com.example.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetCharactersUseCase(
        characterRemoteRepository: CharacterRemoteRepository
    ): GetCharactersRemoteUseCase {

        return GetCharactersRemoteUseCase(characterRemoteRepository)
    }

    @Provides
    fun provideGetCharacterUseCase(
        characterRemoteRepository: CharacterRemoteRepository
    ): GetCharacterRemoteUseCase {

        return GetCharacterRemoteUseCase(characterRemoteRepository)
    }

    @Provides
    fun provideGetCharactersLocaleUseCase(
        characterLocaleRepository: CharacterLocaleRepository
    ): GetCharactersLocaleUseCase {

        return GetCharactersLocaleUseCase(characterLocaleRepository)
    }

    @Provides
    fun provideInsertCharacterUseCase(
        characterLocaleRepository: CharacterLocaleRepository
    ): InsertCharacterLocaleUseCase {

        return InsertCharacterLocaleUseCase(characterLocaleRepository)
    }

    @Provides
    fun provideDeleteCharacterUseCase(
        characterLocaleRepository: CharacterLocaleRepository
    ): DeleteCharacterLocaleUseCase {

        return DeleteCharacterLocaleUseCase(characterLocaleRepository)
    }

    @Provides
    fun provideGetCharacterLocaleUseCase(
        characterLocaleRepository: CharacterLocaleRepository
    ): GetCharacterLocaleUseCase {

        return GetCharacterLocaleUseCase(characterLocaleRepository)
    }
}