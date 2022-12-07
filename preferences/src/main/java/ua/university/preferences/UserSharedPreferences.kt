package ua.university.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSharedPreferences @Inject constructor(
    @ApplicationContext private val appContext: Context
) {

    private val USER_SHARED_PREFERENCES = "USER_SHARED_PREFERENCES"
    private val USER_KEY = "USER_KEY"
    private val TOKENS_KEY = "TOKENS_KEY"

    private val sharedPreferences = appContext.getSharedPreferences(USER_SHARED_PREFERENCES, MODE_PRIVATE)
    private val gson = Gson()

    fun getUser(): UserSP? {
        return gson.fromJson(
            sharedPreferences.getString(USER_KEY, null),
            UserSP::class.java
        )
    }

    fun saveUser(userSP: UserSP) {
        sharedPreferences.edit()
            .putString(
                USER_KEY,
                gson.toJson(userSP)
            )
            .apply()
    }

    fun deleteUser() {
        sharedPreferences.edit()
            .remove(USER_KEY)
            .apply()
    }

    fun getTokens(): Tokens? {
        return gson.fromJson(
            sharedPreferences.getString(TOKENS_KEY, null),
            Tokens::class.java
        )
    }

    fun saveTokens(tokens: Tokens) {
        sharedPreferences.edit()
            .putString(
                TOKENS_KEY,
                gson.toJson(tokens)
            )
            .apply()
    }

    fun deleteTokens() {
        sharedPreferences.edit()
            .remove(TOKENS_KEY)
            .apply()
    }
}