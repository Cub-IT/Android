package com.example.core.data.repository

import com.example.core.util.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun isSignedIn(): Boolean

    fun isSignedInFlow(): Flow<Boolean>

    suspend fun signIn(email: String, password: String): Result<Unit, Exception>

    suspend fun signUp(name: String, email: String, password: String): Result<Unit, Exception>

    suspend fun logOut()

}