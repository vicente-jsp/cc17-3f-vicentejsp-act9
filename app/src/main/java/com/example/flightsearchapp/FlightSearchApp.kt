package com.example.flightsearchapp

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore


private const val INPUT_PREFERENCE_NAME = "input_string"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = INPUT_PREFERENCE_NAME
)

class FlightSearchApp : Application() {
    lateinit var userPreferencesRepository: UserPreferencesRepository
    val database: FlightSearchDatabase by lazy { FlightSearchDatabase.getDatabase(this) }
    override fun onCreate() {
        super.onCreate()
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }

}