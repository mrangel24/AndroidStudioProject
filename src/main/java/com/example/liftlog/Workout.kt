package com.example.liftlog

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_table")
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val exerciseName: String,
    val sets: Int,
    val reps: Int,
    val weight: Double,
    val date: String
)
