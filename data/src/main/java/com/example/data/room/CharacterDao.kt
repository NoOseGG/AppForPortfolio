package com.example.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.GET

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_details")
    suspend fun getCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM character_details WHERE id = :id")
    suspend fun getCharacter(id: Int): CharacterEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(characterEntity: CharacterEntity)

    @Delete
    suspend fun deleteCharacter(characterEntity: CharacterEntity)

}