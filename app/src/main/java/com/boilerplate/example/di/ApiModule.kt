package com.boilerplate.example.di

import com.boilerplate.example.data.network.api.UserApi
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

object ApiModule {
    @Singleton
    @Provides
    fun provideBehaviorApi(retrofit: Retrofit.Builder): UserApi {
        return retrofit
            .build()
            .create(UserApi::class.java)
    }
}