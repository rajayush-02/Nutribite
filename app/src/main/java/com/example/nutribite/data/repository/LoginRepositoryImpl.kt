
package com.example.nutribite.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.nutribite.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LoginRepositoryImpl(context: Context) : PrefsDataStore(context = context, PREF_LOGIN_STATE), LoginRepository  {


    companion object {
        const val PREF_LOGIN_STATE = "user_login_state_pref"
        private val LOGIN_STATE_KEY = booleanPreferencesKey("user_login_state")

    }


    override val loginState: Flow<Boolean>
        get() = mDataStore.data.map { preferences ->
            val uiMode = preferences[LOGIN_STATE_KEY] ?: false
            uiMode
        }

    override suspend fun toggleLoginState() {
        mDataStore.edit { preferences ->
            val loginState = preferences[LOGIN_STATE_KEY] ?: false
            preferences[LOGIN_STATE_KEY] = !loginState
        }
    }




}

abstract class PrefsDataStore(context: Context, fileName: String) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(fileName)
    val mDataStore: DataStore<Preferences> = context.dataStore
}
