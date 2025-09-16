package com.example.smartpill.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "pills")
data class Pill(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val dose: String,
    val timeOfDay: String, // "HH:mm"
    val stock: Int,
    val expiryIso: String // "YYYY-MM-DD"
)
