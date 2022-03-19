package com.cahyadesthian.thedatastore



import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class SettingPreferences private constructor(private val dataStore: DataStore<Preferences>){


    /*
    * Untuk penyimpanan dan pembacaan pengaturan tema
    * yang berupa boolean ke dalam datasotre
    *
    * */
    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map {preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }


    companion object {

        /*
        * Volatile adalah keyword yang digunakan supaya nilai
        * pada suatu variabel tidak dimasukkan ke dalam cache.
        * */
        @Volatile
        private var INSTANCE : SettingPreferences? = null

        /*
        * Penambahan constructor DataStore dan
        * pembuatan buat fungsi berikut untuk membuat class ini menjadi Singleton.
        * */
        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences {
            return INSTANCE?: synchronized(this) {
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}