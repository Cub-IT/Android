package com.example.feature_user.data.remote

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("/login")
    suspend fun signIn(
        @Body data: RequestBody
    )

    @GET("/api/v1/user/new")
    suspend fun signUp(
        @Body data: RequestBody
    )

}