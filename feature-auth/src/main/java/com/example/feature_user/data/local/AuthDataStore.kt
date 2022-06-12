package com.example.feature_user.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class AuthDataStore @Inject constructor(
    @ApplicationContext private val appContext: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "USER_ID_DATA_STORE")
    private val userIdKey = stringPreferencesKey("USER_ID_KEY")
    val userIdKeyFlow = appContext.dataStore.data
        .map { preferences ->
            preferences[userIdKey]
        }

    suspend fun getUserId(): String? = userIdKeyFlow.lastOrNull()

    suspend fun saveUserId(userId: String) {
        appContext.dataStore.edit { settings ->
            settings[userIdKey] = userId
        }
    }

    suspend fun deleteUserId() {
        appContext.dataStore.edit { settings ->
            settings.clear()
        }
    }

}