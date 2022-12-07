package ua.university.auth.data.local

import ua.university.preferences.Tokens
import ua.university.preferences.UserSP
import ua.university.preferences.UserSharedPreferences
import javax.inject.Inject

class AuthSource @Inject constructor(
    private val userSharedPreferences: UserSharedPreferences
) {
    fun saveUser(firstName: String, lastName: String, email: String) {
        userSharedPreferences.saveUser(
            userSP = UserSP(
                firstName = firstName,
                lastName = lastName,
                email = email,
            )
        )
    }

    fun saveTokens(access: String, refresh: String) {
        userSharedPreferences.saveTokens(Tokens(access, refresh))
    }
}