package com.example.feature_user.data.remote

import retrofit2.http.GET
import retrofit2.http.POST

interface SignApi {

    @POST("/api/v1/user/new")
    fun signIn()

    @GET("/login")
    fun signUp()

}