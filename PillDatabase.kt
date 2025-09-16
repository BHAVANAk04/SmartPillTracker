package com.example.smartpill.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pill::class], version = 1, exportSchema = false)
abstract class PillDatabase: RoomDatabase() {
    abstract fun pillDao(): PillDao

    companion object {
        @Volatile private var INSTANCE: PillDatabase? = null
        fun getDatabase(context: Context): PillDatabase {
            return INSTANCE ?: synchronized(this) {
                val inst = Room.databaseBuilder(context.applicationContext, PillDatabase::class.java, "pill_db").build()
                INSTANCE = inst
                inst
            }
        }
    }
}
