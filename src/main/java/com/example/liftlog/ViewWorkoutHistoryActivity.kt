package com.example.liftlog

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.liftlog.databinding.ActivityViewWorkoutHistoryBinding

class ViewWorkoutHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewWorkoutHistoryBinding
    private val workoutViewModel: WorkoutViewModel by viewModels {
        WorkoutViewModelFactory(WorkoutRepository(WorkoutDatabase.getDatabase(this).workoutDao()))
    }
    private lateinit var adapter: WorkoutHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewWorkoutHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSwipeToDelete()

        workoutViewModel.allWorkouts.observe(this) { workouts ->
            adapter.submitList(workouts) // Update adapter with new data
        }

        binding.fabAddWorkout.setOnClickListener {
        }
    }

    private fun setupRecyclerView() {
        adapter = WorkoutHistoryAdapter { workout ->
            workoutViewModel.deleteWorkout(workout)
        }
        binding.workoutHistoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ViewWorkoutHistoryActivity)
            adapter = this@ViewWorkoutHistoryActivity.adapter
        }
    }

    private fun setupSwipeToDelete() {
        val swipeToDeleteCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false // No move functionality
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition // Updated from adapterPosition
                adapter.deleteItem(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.workoutHistoryRecyclerView)
    }

}
