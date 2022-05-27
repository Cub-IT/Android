package com.example.feature_user.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class SignDataStore(
    private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "signDataStore")
    private val userIdKey = stringPreferencesKey("example_counter")
    private val pref = context.dataStore.data
        .map { preferences ->
            preferences[userIdKey]
        }

    suspend fun getUserId(): String? = pref.first()

    suspend fun saveUserId(userId: String) {
        val t = context.dataStore.edit { settings ->
            settings[userIdKey] = userId
        }
    }

}