package com.example.homeworks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_stat.*
import kotlinx.android.synthetic.main.item_racer.tv_name
import kotlinx.android.synthetic.main.item_racer.tv_team

class StatisticActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat)

        val racer = intent?.extras?.getString(FULL_NAME) ?: "Unknown"
        val age = intent?.extras?.getInt(RACER_AGE) ?: "Unknown"
        val team = intent?.extras?.getString(TEAM) ?: "Unknown"
        val granPrix = intent?.extras?.getInt(GRANPRIX) ?: "Unknown"
        val points = intent?.extras?.getInt(POINTS) ?: "Unknown"
        val imageId = intent?.extras?.getInt(IMAGE_ID) ?: "Unknown"

        tv_name.text = racer
        tv_team.text = team
        tv_age_value.text = age.toString()
        tv_grandprix_value.text = granPrix.toString()
    }

    companion object {
        private const val FULL_NAME = "fullName"
        private const val RACER_AGE = "age"
        private const val TEAM = "team"
        private const val GRANPRIX = "granPrix"
        private const val POINTS = "points"
        private const val IMAGE_ID = "image"

        fun createIntent(activity: Activity, fullName: String, age: Int, team: String, granPrix: Int,
                         points: Int, imageId: Int) =
            Intent(activity, StatisticActivity::class.java).apply {
                putExtra(FULL_NAME, fullName)
                putExtra(RACER_AGE, age)
                putExtra(TEAM, team)
                putExtra(GRANPRIX, granPrix)
                putExtra(POINTS, points)
                putExtra(IMAGE_ID, imageId)
            }
    }
}
