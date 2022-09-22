package com.example.data.di

import com.example.data.repository.CharacterRemoteRepositoryImpl
import com.example.domain.repository.CharacterRemoteRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindCharacterRemoteRepository(
        impl: CharacterRemoteRepositoryImpl
    ): CharacterRemoteRepository
}
