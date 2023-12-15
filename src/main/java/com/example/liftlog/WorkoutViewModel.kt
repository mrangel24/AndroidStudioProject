package com.example.liftlog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WorkoutViewModel(private val repository: WorkoutRepository) : ViewModel() {

    val allWorkouts: LiveData<List<Workout>> = repository.allWorkouts

    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch {
            repository.delete(workout)
        }
    }
}


