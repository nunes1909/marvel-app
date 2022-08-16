package com.example.marvelapp.data.local.dao

import androidx.room.*
import com.example.marvelapp.data.model.character.model.Character
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
