package ua.university.user.remote.api

import retrofit2.Retrofit
import ua.university.user.model.LogInUser
import ua.university.user.model.SignUpUser
import ua.university.user.remote.entry.LogInRequestEntry
import ua.university.user.remote.entry.UserResponseEntry
import ua.university.user.remote.entry.SignUpRequestEntry
import ua.university.util.result.NetworkResult

class AuthService(private val retrofit: Retrofit) {
    private val authApi: AuthApi by lazy { retrofit.create(AuthApi::class.java) }

    suspend fun logIn(user: LogInUser): NetworkResult<UserResponseEntry,> {
        val logInRequestEntry = LogInRequestEntry(
            email = user.email,
            password = user.password
        )
        return authApi.logIn(logInRequestEntry = logInRequestEntry)
    }

    suspend fun signUp(user: SignUpUser): NetworkResult<UserResponseEntry> {
        val signUpRequestEntry = SignUpRequestEntry(
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            password = user.password
        )
        return authApi.signUp(signUpRequestEntry = signUpRequestEntry)
    }
}