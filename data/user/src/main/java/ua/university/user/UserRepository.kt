package ua.university.user

import ua.university.user.model.LogInUser
import ua.university.user.model.SignUpUser
import ua.university.network.result.NetworkResult

interface UserRepository {
    suspend fun logIn(user: LogInUser): NetworkResult<Unit>

    suspend fun signUp(user: SignUpUser): NetworkResult<Unit>
}