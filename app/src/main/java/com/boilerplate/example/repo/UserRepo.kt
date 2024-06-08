package com.boilerplate.example.repo

import com.boilerplate.example.data.db.dao.UserDao
import com.boilerplate.example.data.db.entity.User
import com.boilerplate.example.data.network.api.UserApi
import com.boilerplate.example.utils.PreferenceUtil

class UserRepo(
    private val userApi: UserApi,
    private val userDao: UserDao,
    private val preferenceUtil: PreferenceUtil
){
    suspend fun getUser() = userApi.getUser(
        preferenceUtil.getString(PreferenceUtil.KEY_COOKIE) ?: ""
    )
    //Local Calls
    suspend fun addUser(user: User) = userDao.addUser(user)
    suspend fun getUserById(id: Int) = userDao.getUserById(id)
    suspend fun updateUser(id: Int, name: String, email: String, password: String) = userDao.updateUser(id, name, email, password)
    suspend fun deleteUser(id: Int) = userDao.deleteUser(id)
    suspend fun getAllUsers() = userDao.getAllUsers()
    suspend fun deleteAllUsers() = userDao.deleteAllUsers()

}
