package com.example.homeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homeworks.ui.home.HomeFragment
import com.example.homeworks.ui.notifications.RaceTracksFragment
import com.example.homeworks.ui.racer.RacersFragment
import kotlinx.android.synthetic.main.activity_formula_one.*

class FormulaOneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formula_one)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container_fragment, RacersFragment.newInstance())
            addToBackStack(RacersFragment::class.java.name)
            commit()
        }

        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    navigateToFragment(HomeFragment.newInstance())
                    true
                }
                R.id.navigation_dashboard -> {
                    navigateToFragment(RacersFragment.newInstance())
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
