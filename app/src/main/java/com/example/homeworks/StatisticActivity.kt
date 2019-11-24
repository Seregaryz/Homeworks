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

        val racer = intent?.extras?.getString(EXTRA_FULL_NAME) ?: "Unknown"
        val age = intent?.extras?.getInt(EXTRA_RACER_AGE) ?: 20
        val team = intent?.extras?.getString(EXTRA_TEAM) ?: "Unknown"
        val grandPrix = intent?.extras?.getInt(EXTRA_GRANDPRIX) ?: 0
        val points = intent?.extras?.getInt(EXTRA_POINTS) ?: 0
        val imageId = intent?.extras?.getInt(EXTRA_IMAGE_ID) ?: 10

        tv_name.text = racer
        tv_team.text = team
        tv_age_value.text = age.toString()
        tv_grandprix_value.text = grandPrix.toString()
        tv_points_value.text = points.toString()
        racer_img.setImageResource(imageId)
    }

    companion object {
        private const val EXTRA_FULL_NAME = "fullName"
        private const val EXTRA_RACER_AGE = "age"
        private const val EXTRA_TEAM = "team"
        private const val EXTRA_GRANDPRIX = "granPrix"
        private const val EXTRA_POINTS = "points"
        private const val EXTRA_IMAGE_ID = "image"

        fun createIntent(activity: Activity, racer: Racer) =
            Intent(activity, StatisticActivity::class.java).apply {
                putExtra(EXTRA_FULL_NAME, racer.fullName)
                putExtra(EXTRA_RACER_AGE, racer.age)
                putExtra(EXTRA_TEAM, racer.team)
                putExtra(EXTRA_GRANDPRIX, racer.grandPrix)
                putExtra(EXTRA_POINTS, racer.points)
                putExtra(EXTRA_IMAGE_ID, racer.imageId)
            }
    }
}
