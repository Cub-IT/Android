package ua.university.auth.data.remote.api

import retrofit2.Retrofit
import ua.university.auth.data.remote.entry.SignInRequestEntry
import ua.university.auth.data.remote.entry.SignResponseEntry
import ua.university.auth.data.remote.entry.SignUpRequestEntry
import ua.university.network.result.NetworkResult
import javax.inject.Inject
import javax.inject.Named

class AuthService @Inject constructor(
    @Named("authRetrofit") private val retrofit: Retrofit
) {

    private val authApi: AuthApi by lazy { retrofit.create(AuthApi::class.java) }

    suspend fun signIn(email: String, password: String): NetworkResult<SignResponseEntry> {
        val signInRequestEntry = SignInRequestEntry(
            email = email,
            password = password
        )
        return authApi.signIn(signInRequestEntry = signInRequestEntry)
    }

    suspend fun signUp(firstName: String, lastName: String, email: String, password: String): NetworkResult<Unit> {
        val signUpRequestEntry = SignUpRequestEntry(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password
        )
        return authApi.signUp(signUpRequestEntry = signUpRequestEntry)
    }
}