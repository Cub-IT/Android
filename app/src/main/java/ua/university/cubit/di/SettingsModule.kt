package ua.university.cubit.di

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.university.auth.data.local.AuthSource
import ua.university.auth.data.local.UserItem
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Binds
    @Singleton
    abstract fun provideUserSource(userSharedPreferences: UserSharedPreferences): AuthSource

}

@Singleton
class UserSharedPreferences @Inject constructor(
    @ApplicationContext private val appContext: Context
) : AuthSource {
    private val USER_SHARED_PREFERENCES = "USER_SHARED_PREFERENCES"
    private val USER_KEY = "USER_KEY"
    private val COOKIES_KEY = "COOKIES_KEY"

    private val sharedPreferences = appContext.getSharedPreferences(USER_SHARED_PREFERENCES, MODE_PRIVATE)
    private val gson = Gson()

    fun getUser(): UserItem? {
        return gson.fromJson(
            sharedPreferences.getString(USER_KEY, null),
            UserItem::class.java
        )
    }

    @SuppressLint("CommitPrefEdits")
    override fun saveUser(userItem: UserItem) {
        sharedPreferences.edit()
            .putString(
                USER_KEY,
                gson.toJson(userItem)
            )
            .apply()
    }

    fun deleteUser() {
        sharedPreferences.edit()
            .remove(USER_KEY)
            .apply()
    }
}