package com.boilerplate.example.data.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.boilerplate.example.data.db.entity.User

interface UserDao {
    //create user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User): Long

    //read user
    @Query("Select * from user where id = :id")
    suspend fun getUserById(id: Int): User?

    //update user
    @Query("Update user set name = :name, email = :email, password = :password where id = :id")
    suspend fun updateUser(id: Int, name: String, email: String, password: String): Int

    //delete user
    @Query("Delete from user where id = :id")
    suspend fun deleteUser(id: Int): Int

    //get all users
    @Query("Select * from user")
    suspend fun getAllUsers(): List<User>

    //delete all users
    @Query("Delete from user")
    suspend fun deleteAllUsers(): Int

}