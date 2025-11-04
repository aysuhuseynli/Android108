package com.example.android108.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insert(userInfo: UserEntity)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<UserEntity>
}