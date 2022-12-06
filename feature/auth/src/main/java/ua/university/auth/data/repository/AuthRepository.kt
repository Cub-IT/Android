package ua.university.auth.data.repository

import ua.university.auth.data.local.AuthSource
import ua.university.auth.data.remote.api.AuthService
import ua.university.auth.data.remote.entry.toUserItem
import ua.university.network.result.NetworkResult
import ua.university.network.result.Result
import ua.university.network.result.onFailure
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val userSource: AuthSource,
    private val authService: AuthService
) {

    suspend fun signIn(email: String, password: String): NetworkResult<Unit> {
        val userItem = authService.signIn(email = email, password = password)
            .onFailure { return it }
            .toUserItem()

        userSource.saveUser(userItem)
        return Result.Success(Unit)
    }

    suspend fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): NetworkResult<Unit> {
        val userItem = authService.signUp(firstName = firstName, lastName = lastName, email = email, password = password)
            .onFailure { return it }
            .toUserItem()

        userSource.saveUser(userItem)
        return Result.Success(Unit)
    }

}