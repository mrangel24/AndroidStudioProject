package com.example.liftlog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.liftlog.databinding.WorkoutItemBinding

class WorkoutHistoryAdapter(private val onDeleteClick: (Workout) -> Unit) : ListAdapter<Workout, WorkoutHistoryAdapter.WorkoutViewHolder>(WorkoutsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        return WorkoutViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    fun deleteItem(position: Int) {
        val currentWorkout = getItem(position)
        onDeleteClick(currentWorkout)
    }

    class WorkoutViewHolder(private val binding: WorkoutItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(workout: Workout) {
            binding.exerciseName.text = workout.exerciseName
            binding.workoutDetails.text = "Weight: ${workout.weight}, Sets: ${workout.sets}, Reps: ${workout.reps}, Date: ${workout.date}"
        }

        companion object {
            fun create(parent: ViewGroup): WorkoutViewHolder {
                val binding = WorkoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return WorkoutViewHolder(binding)
            }
        }
    }

    class WorkoutsComparator : DiffUtil.ItemCallback<Workout>() {
        override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
