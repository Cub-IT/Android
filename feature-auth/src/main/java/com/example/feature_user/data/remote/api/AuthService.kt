package com.example.feature_user.data.remote.api

import com.example.core.data.BaseRetrofit
import com.example.feature_user.data.remote.AuthApi
import com.example.feature_user.data.remote.entry.SignInRequestEntry
import com.example.feature_user.data.remote.entry.SignUpRequestEntry
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

class AuthService {

    private val authApi: AuthApi by lazy { BaseRetrofit.create(AuthApi::class.java) }

    suspend fun signIn(email: String, password: String): String {
        val signInRequestEntry = SignInRequestEntry(
            email = email,
            password = password
        )
        return authApi.signIn(signInRequestEntry = signInRequestEntry).token
    }

    suspend fun signUp(name: String, email: String, password: String) {
        val signUpRequestEntry = SignUpRequestEntry(
            name = name,
            email = email,
            password = password
        )
        authApi.signUp(signUpRequestEntry = signUpRequestEntry)
    }
}