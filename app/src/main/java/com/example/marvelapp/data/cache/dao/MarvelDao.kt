package com.example.marvelapp.data.cache.dao

import androidx.room.*
import com.example.marvelapp.data.character.model.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(character: Character)

    @Query("SELECT * FROM CHARACTER ORDER BY name")
    fun getAll(): Flow<List<Character>>

    @Delete
    suspend fun deleta(character: Character)
}