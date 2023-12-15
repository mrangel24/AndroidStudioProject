package com.example.liftlog

import androidx.lifecycle.LiveData

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    val allWorkouts: LiveData<List<Workout>> = workoutDao.getAllWorkouts()

    suspend fun delete(workout: Workout) {
        workoutDao.delete(workout)
    }
}

