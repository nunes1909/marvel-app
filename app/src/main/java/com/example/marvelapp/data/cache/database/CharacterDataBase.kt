package com.example.marvelapp.data.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.marvelapp.data.cache.converter.ThumbnailConverter
import com.example.marvelapp.data.cache.dao.MarvelDao
import com.example.marvelapp.data.character.model.Character
import com.example.marvelapp.util.constants.Constants.DATA_BASE_NAME

@Database(
    entities = [Character::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    ThumbnailConverter::class
)
abstract class MarvelDataBase : RoomDatabase() {

    abstract fun getMarvelDao(): MarvelDao

    companion object {

        @Volatile
        private var db: MarvelDataBase? = null

        fun getInstance(context: Context): MarvelDataBase {
            return db ?: Room.databaseBuilder(
                context,
                MarvelDataBase::class.java,
                DATA_BASE_NAME
            ).build()
                .also {
                    db = it
                }
        }
    }
}