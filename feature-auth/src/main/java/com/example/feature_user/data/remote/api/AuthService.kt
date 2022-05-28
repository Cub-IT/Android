package com.example.feature_user.data.remote.api

import com.example.feature_user.data.remote.AuthApi
import com.example.feature_user.data.remote.entry.SignInRequestEntry
import com.example.feature_user.data.remote.entry.SignUpRequestEntry
import retrofit2.Retrofit
import javax.inject.Inject

class AuthService @Inject constructor(
    private val retrofit: Retrofit
) {

    private val authApi: AuthApi by lazy { retrofit.create(AuthApi::class.java) }

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