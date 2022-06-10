package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.model.LocalRestaurant

@Database(
    entities = [LocalRestaurant::class],
    version = 2,
    exportSchema = false
)
abstract class RestaurantsDb: RoomDatabase() {
    abstract val dao: RestaurantsDao
}