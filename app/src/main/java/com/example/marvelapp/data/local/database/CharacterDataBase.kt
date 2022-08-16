package com.example.marvelapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.marvelapp.data.local.converter.ThumbnailConverter
import com.example.marvelapp.data.local.dao.MarvelDao
import com.example.marvelapp.data.model.character.model.Character
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
        fun getInstance(context: Context): MarvelDataBase {
            return Room.databaseBuilder(
                context,
                MarvelDataBase::class.java,
                DATA_BASE_NAME
            ).build()
        }
    }
}
