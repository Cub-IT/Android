package com.example.feature_user.data.remote.api

import com.example.core.util.Result
import com.example.core.util.map
import com.example.feature_user.data.remote.entry.SignInRequestEntry
import com.example.feature_user.data.remote.entry.SignUpRequestEntry
import retrofit2.Retrofit
import javax.inject.Inject

class AuthService @Inject constructor(
    private val retrofit: Retrofit
) {

    private val authApi: AuthApi by lazy { retrofit.create(AuthApi::class.java) }

    suspend fun signIn(email: String, password: String): Result<String, Exception> {
        val signInRequestEntry = SignInRequestEntry(
            email = email,
            password = password
        )
        val response = authApi.signIn(signInRequestEntry = signInRequestEntry)
        return response.map { it.token }
    }

    suspend fun signUp(name: String, email: String, password: String): Result<Unit, Exception> {
        val signUpRequestEntry = SignUpRequestEntry(
            name = name,
            email = email,
            password = password
        )
        return authApi.signUp(signUpRequestEntry = signUpRequestEntry)
    }
}