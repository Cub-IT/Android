package ua.university.user

import ua.university.user.local.UserSource
import ua.university.user.model.LogInUser
import ua.university.user.model.SignUpUser
import ua.university.user.remote.api.AuthService
import ua.university.user.remote.entry.toUser
import ua.university.util.result.NetworkResult
import ua.university.util.result.Result
import ua.university.util.result.onFailure
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userSource: UserSource,
    private val authService: AuthService
) {
    suspend fun logIn(user: LogInUser): NetworkResult<Unit> {
        val userItem = authService.logIn(user = user)
            .onFailure { return it }
            .toUser()

        userSource.saveUser(userItem)
        return Result.Success(Unit)
    }

    suspend fun signUp(user: SignUpUser): NetworkResult<Unit> {
        val userItem = authService.signUp(user = user)
            .onFailure { return it }
            .toUser()

        userSource.saveUser(userItem)
        return Result.Success(Unit)
    }
}