package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.room.CharacterDao
import com.example.data.room.CharacterDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideCharacterDatabase(
        context: Context
    ): CharacterDatabase {

        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "character_database.db"
        )
            .build()
    }

    @Provides
    fun provideCharacterDao(characterDatabase: CharacterDatabase): CharacterDao {
        return characterDatabase.getCharacterDao()
    }
}