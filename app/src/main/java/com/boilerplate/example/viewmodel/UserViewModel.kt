package com.boilerplate.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boilerplate.example.data.db.entity.User
import com.boilerplate.example.repo.UserRepo
import com.boilerplate.example.utils.LogUtil
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepo: UserRepo
) : ViewModel() {
    suspend fun getUser() {
        viewModelScope.launch {
            when (val res = userRepo.getUser()) {
                is NetworkResponse.Success -> {
                    val user = res.body
                    addUser(user)
                }

                is NetworkResponse.ServerError -> {
                    LogUtil.e(res.code.toString())
                    LogUtil.e(res.body?.message)

                }

                is NetworkResponse.NetworkError -> {
                    res.error.printStackTrace()
                }

                is NetworkResponse.UnknownError -> {
                    res.error.printStackTrace()
                }
            }
        }
    }

    suspend fun addUser(user: User) = userRepo.addUser(user)
    suspend fun getUserById(id: Int) = userRepo.getUserById(id)
    suspend fun updateUser(id: Int, name: String, email: String, password: String) =
        userRepo.updateUser(id, name, email, password)

    suspend fun deleteUser(id: Int) = userRepo.deleteUser(id)
    suspend fun getAllUsers() = userRepo.getAllUsers()
    suspend fun deleteAllUsers() = userRepo.deleteAllUsers()
}