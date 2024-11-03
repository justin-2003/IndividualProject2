package com.example.individualproject2

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.individualproject2.data.AppPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AppStorage ( private val context: Context) {
    companion object{
        private val Context.dataStore by
        preferencesDataStore(name = "app_preferences")

        private object PreferencesKeys {
            val USERNAME = stringPreferencesKey("password")
            val PASSWORD = stringPreferencesKey("user_name")
            val LASTSCORE = intPreferencesKey("last_score")
            val LASTAMOUNT = intPreferencesKey("last_amount")
        }
    }

    val appPreferenceFlow: Flow<AppPreference> = context.dataStore.data
        .map { preferences ->
            val userName = preferences[PreferencesKeys.USERNAME] ?: ""
            val password = preferences[PreferencesKeys.PASSWORD] ?: ""
            val lastScore = preferences[PreferencesKeys.LASTSCORE] ?: 0
            val lastAmount = preferences[PreferencesKeys.LASTAMOUNT] ?: 0
            AppPreference(userName, password,lastScore, lastAmount)
        }

    //Functions that save the username, password, last Score and last Amount
    suspend fun saveUsername(username: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.USERNAME] = username
        }
    }
    suspend fun savePassword(password: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.PASSWORD] = password
        }
    }

    suspend fun saveLastScore(lastScore: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.LASTSCORE] = lastScore
        }
    }

    suspend fun saveLastAmount(lastAmount: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.LASTAMOUNT] = lastAmount
        }
    }
}