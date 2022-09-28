package ua.university.user.remote.api

import retrofit2.Retrofit
import ua.university.user.remote.entry.LogInRequestEntry
import ua.university.user.remote.entry.SignResponseEntry
import ua.university.user.remote.entry.SignUpRequestEntry
import ua.university.util.result.NetworkResult

class AuthService(private val retrofit: Retrofit) {
    private val authApi: AuthApi by lazy { retrofit.create(AuthApi::class.java) }

    suspend fun logIn(email: String, password: String): NetworkResult<SignResponseEntry,> {
        val logInRequestEntry = LogInRequestEntry(
            email = email,
            password = password
        )
        return authApi.logIn(logInRequestEntry = logInRequestEntry)
    }

    suspend fun signUp(firstName: String, lastName: String, email: String, password: String): NetworkResult<SignResponseEntry> {
        val signUpRequestEntry = SignUpRequestEntry(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password
        )
        return authApi.signUp(signUpRequestEntry = signUpRequestEntry)
    }
}