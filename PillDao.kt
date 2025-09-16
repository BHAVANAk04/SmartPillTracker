package com.example.smartpill.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PillDao {
    @Query("SELECT * FROM pills ORDER BY name")
    fun getAll(): LiveData<List<Pill>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pill: Pill): Long

    @Update
    suspend fun update(pill: Pill)

    @Delete
    suspend fun delete(pill: Pill)
}
