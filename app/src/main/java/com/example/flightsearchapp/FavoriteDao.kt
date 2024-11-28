package com.example.flightsearchapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import kotlinx.coroutines.flow.Flow

@Dao
@TypeConverters(AirportConverter::class)
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favorite: Favorite)

    @Delete
    suspend fun removeFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite ORDER BY departure_code ASC")
    fun getAll(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite WHERE departure_code = :departureAirport AND destination_code = :destinationAirport")
    suspend fun getFavorite(departureAirport: Airport, destinationAirport:Airport): Favorite?
}