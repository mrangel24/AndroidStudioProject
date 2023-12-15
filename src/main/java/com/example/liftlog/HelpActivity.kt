package com.example.liftlog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.liftlog.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helpText = """
            Welcome to LiftLog, your personal workout tracking app!

            With LiftLog, you can:
            - Log new workouts, including exercises, sets, reps, and weights.
            - View your workout history to track your progress.
            - Customize your experience with theme and display settings.

            To get started:
            - Use the 'Log New Workout' button to add a new workout entry.
            - Check your past workouts with the 'View Workout History' button.
            - Customize your app experience in the Preferences section.

            LiftLog is here to support your fitness journey every step of the way!
        """.trimIndent()

        binding.textViewHelp.text = helpText
    }
}
