package com.example.liftlog

import java.text.SimpleDateFormat
import java.util.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import android.view.MenuItem
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.liftlog.databinding.ActivityLogNewWorkoutBinding

class LogNewWorkoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogNewWorkoutBinding

    private val database by lazy { WorkoutDatabase.getDatabase(this) }
    private val workoutDao by lazy { database.workoutDao() }

    private fun insertWorkout(workout: Workout) {
        lifecycleScope.launch(Dispatchers.IO) { // Use IO Dispatcher for database operations
            workoutDao.insert(workout)
            // Optionally navigate back or show a confirmation message in the main thread
            launch(Dispatchers.Main) {
                // UI updates or navigation
                finish()
            }
        }
    }


    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogNewWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enable the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Log New Workout"

        binding.submitWorkoutButton.setOnClickListener {
            val exerciseName = binding.exerciseName.text.toString()
            val weight = binding.weight.text.toString().toDoubleOrNull() ?: 0.0
            val sets = binding.sets.text.toString().toIntOrNull() ?: 0
            val reps = binding.reps.text.toString().toIntOrNull() ?: 0

            val workout = Workout(
                exerciseName = exerciseName,
                weight = weight,
                sets = sets,
                reps = reps,
                date = getCurrentDate() // You need to implement this method to get the current date
            )

            // Insert the data into the database
            insertWorkout(workout)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish() // Replaces onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
