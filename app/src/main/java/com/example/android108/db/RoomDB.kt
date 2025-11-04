package com.example.android108.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 2, exportSchema = false)
abstract class RoomDB: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun getInstance(context: Context): RoomDB {
            val db = Room.databaseBuilder(
                context,
                RoomDB::class.java,
                "RoomDB"
            ).build()
            return db
        }
    }
}