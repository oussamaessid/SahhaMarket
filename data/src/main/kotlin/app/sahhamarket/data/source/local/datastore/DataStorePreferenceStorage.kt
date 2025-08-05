package app.sahhamarket.data.source.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import app.sahhamarket.data.source.local.datastore.DataStorePreferenceStorage.PreferenceKeys.FIRST_OPEN_APP
import app.sahhamarket.domain.datastore.PreferenceStorage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePreferenceStorage @Inject constructor(context: Context) : PreferenceStorage {

    private val Context.store by preferencesDataStore(name = PREFS_NAME)
    private val dataStore = context.store

    override suspend fun setIsFirstInstall() {
        dataStore.edit {
            it[FIRST_OPEN_APP] = true
        }
    }

    override suspend fun getIsFirstInstall() =
        dataStore.data.map { it[FIRST_OPEN_APP] ?: false }.first()

    object PreferenceKeys {
        val FIRST_OPEN_APP = booleanPreferencesKey("FIRST_OPEN")
    }

    companion object {
        const val PREFS_NAME = "template_datastore"
    }
}