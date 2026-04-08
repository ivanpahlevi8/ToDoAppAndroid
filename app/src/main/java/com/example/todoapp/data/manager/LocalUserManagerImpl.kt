package com.example.todoapp.data.manager

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.todoapp.core.value.Constants
import com.example.todoapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val application: Application,
) : LocalUserManager {
    override suspend fun setUserOnBoard() {
        // set datastore user on board as true
        application.datastore.edit {
            preferences -> preferences[PreferencesKey.APP_ENTRY] = true
        }
    }

    override fun getUserOnBoard(): Flow<Boolean> {
        return application.datastore.data.map {
            preference -> preference[PreferencesKey.APP_ENTRY] ?: false
        }
    }

    override suspend fun setUserLogIn() {
        application.datastore.edit {
            preferences -> preferences[PreferencesKey.USER_LOGGED_IN] = true
        }
    }

    override suspend fun setUserLogOut() {
        application.datastore.edit {
            preferences -> preferences[PreferencesKey.USER_LOGGED_IN] = false
        }
    }

    override fun getUserLoggedIn(): Flow<Boolean> {
        return application.datastore.data.map {
                preference -> preference[PreferencesKey.USER_LOGGED_IN] ?: false
        }
    }

    // add data store to application context
    val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = Constants.PREFERENCES_KEYS)

    // create preferences key
    object PreferencesKey {
        val APP_ENTRY = booleanPreferencesKey(name = Constants.PREFERENCES_KEYS)
        val USER_LOGGED_IN = booleanPreferencesKey(name = Constants.USER_LOGIN_KEYS)
    }
}