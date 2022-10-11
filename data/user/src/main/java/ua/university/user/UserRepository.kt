package ua.university.user

import kotlinx.coroutines.flow.Flow
import ua.university.user.model.LogInUser
import ua.university.user.model.SignUpUser
import ua.university.network.result.NetworkResult
import ua.university.user.model.User

interface UserRepository {
    suspend fun logIn(user: LogInUser): NetworkResult<Unit>

    suspend fun signUp(user: SignUpUser): NetworkResult<Unit>

    fun isAuthorized(): Boolean

    fun getCurrent(): Flow<User>

    fun getById(userId: String): Flow<User>
}