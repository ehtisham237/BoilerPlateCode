package com.boilerplate.example.data.network.api

import com.google.common.net.HttpHeaders.COOKIE
import com.haroldadmin.cnradapter.NetworkResponse
import com.boilerplate.example.constant.WebServiceConstant
import com.boilerplate.example.data.db.entity.User
import com.boilerplate.example.data.model.apiresponse.ErrorResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {

    @GET(value = WebServiceConstant.LOGIN_URL)
    suspend fun getUser(
        @Header(COOKIE) sessionIdAndToken: String,
    ): NetworkResponse<User, ErrorResponse>
}