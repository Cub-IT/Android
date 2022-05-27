package com.example.feature_user.data.remote.api

import com.example.core.data.BaseRetrofit
import com.example.feature_user.data.remote.AuthApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

class AuthService {
    private val authApi: AuthApi by lazy { BaseRetrofit.create(AuthApi::class.java) }

    suspend fun signIn(email: String, password: String) = JSONObject()
        .apply {
            put("email", email)
            put("password", password)
        }.let {
            RequestBody.create("application/json".toMediaTypeOrNull(), it.toString())
        }.run {
            authApi.signIn(this)
        }

    suspend fun signUp(email: String, password: String, name: String) = JSONObject()
        .apply {
            put("email", email)
            put("password", password)
            put("name", name)
        }.let {
            RequestBody.create("application/json".toMediaTypeOrNull(), it.toString())
        }.run {
            authApi.signIn(this)
        }
}