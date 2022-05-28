package com.example.feature_user.data.repository

import com.example.core.data.repository.AuthRepository
import com.example.feature_user.data.local.AuthDataStore
import com.example.feature_user.data.remote.api.AuthService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataStore: AuthDataStore,
    private val authService: AuthService
) : AuthRepository {

    override suspend fun isSignedIn(): Boolean {
        return authDataStore.getUserId() != null
    }

    override fun isSignedInFlow(): Flow<Boolean> {
        return authDataStore.userIdKeyFlow.mapLatest { token ->
            token != null
        }
    }

    override suspend fun signIn(email: String, password: String) {
        val userToken = authService.signIn(email = email, password = password)
        authDataStore.saveUserId(userToken)
    }

    override suspend fun signUp(name: String, email: String, password: String) {
        authService.signUp(name = name, email = email, password = password)
    }

    override suspend fun logOut() {
        authDataStore.deleteUserId()
    }

}