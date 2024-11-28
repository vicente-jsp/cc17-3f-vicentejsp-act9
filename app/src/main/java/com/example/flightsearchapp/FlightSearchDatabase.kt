package com.example.flightsearchapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Airport::class, Favorite::class], version = 1, exportSchema = false)
abstract class FlightSearchDatabase : RoomDatabase() {
    abstract fun airportDao(): AirportDao

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var Instance: FlightSearchDatabase? = null

        fun getDatabase(context: Context): FlightSearchDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context, FlightSearchDatabase::class.java, "flight_search.db"
                )
                    .createFromAsset("database/flight_search.db")
                    .addTypeConverter(AirportConverter())
                    .build().also {
                        Instance = it
                    }
            }
        }

    }
}