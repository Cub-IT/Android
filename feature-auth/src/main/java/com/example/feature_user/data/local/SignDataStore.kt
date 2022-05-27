package com.example.feature_user.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.map


class SignDataStore(
    private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "USER_ID_DATA_STORE")
    private val userIdKey = stringPreferencesKey("USER_ID_KEY")
    private val pref = context.dataStore.data
        .map { preferences ->
            preferences[userIdKey]
        }

    suspend fun getUserId(): String? = pref.lastOrNull()

    suspend fun saveUserId(userId: String) {
        val t = context.dataStore.edit { settings ->
            settings[userIdKey] = userId
        }
    }

    suspend fun deleteUserId() {
        val t = context.dataStore.edit { settings ->
            settings.clear()
        }
    }

}