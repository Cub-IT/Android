package ua.university.user

import ua.university.user.local.UserSource
import ua.university.user.remote.api.AuthService
import ua.university.user.remote.entry.toUser
import ua.university.util.result.NetworkResult
import ua.university.util.result.Result
import ua.university.util.result.onFailure

class UserRepository(
    private val userSource: UserSource,
    private val authService: AuthService
) {
    suspend fun signIn(email: String, password: String): NetworkResult<Unit> {
        val userItem = authService.logIn(email = email, password = password)
            .onFailure { return it }
            .toUser()

        userSource.saveUser(userItem)
        return Result.Success(Unit)
    }

    suspend fun signUp(firstName: String, lastName: String, email: String, password: String): NetworkResult<Unit> {
        val userItem = authService.signUp(firstName = firstName, lastName = lastName, email = email, password = password)
            .onFailure { return it }
            .toUser()

        userSource.saveUser(userItem)
        return Result.Success(Unit)
    }
}