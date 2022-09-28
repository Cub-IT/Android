package ua.university.user

import ua.university.util.result.NetworkResult

class UserRepository {
    suspend fun signIn(email: String, password: String): NetworkResult<Unit> {
        TODO()
    }

    suspend fun signUp(firstName: String, lastName: String, email: String, password: String): NetworkResult<Unit> {
        TODO()
    }
}