package ua.university.settings.profile.data.repository

import ua.university.preferences.UserSP
import ua.university.preferences.UserSharedPreferences
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val userSharedPreferences: UserSharedPreferences
) {
    fun getUser(): UserSP {
        return userSharedPreferences.getUser()
            ?: throw IllegalStateException("User is authorized but there is no user's data on the phone.")
    }

    fun logout() {
        userSharedPreferences.deleteUser()
    }
}