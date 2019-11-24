package com.example.homeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homeworks.ui.dashboard.DashboardFragment
import com.example.homeworks.ui.home.HomeFragment
import com.example.homeworks.ui.notifications.RaceTracksFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_formula_one.*

class FormulaOneActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formula_one)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container_fragment, DashboardFragment.newInstance())
            addToBackStack(DashboardFragment::class.java.name)
            commit()
        }

        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    navigateToFragment(HomeFragment.newInstance())
                    true
                }
                R.id.navigation_dashboard -> {
                    navigateToFragment(DashboardFragment.newInstance())
                    true
                }
                R.id.navigation_notifications -> {
                    navigateToFragment(RaceTracksFragment.newInstance())
                    true
                }
                else -> false
            }

        }
    }


    private fun navigateToFragment(fragment: Fragment){
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.container_fragment, fragment)
                addToBackStack(fragment::class.java.name)
                commit()
            }
        }
    }
}


