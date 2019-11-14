package com.example.homeworks

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.homeworks.ui.fifth.MemesFragment
import com.example.homeworks.ui.first.ReviewFragment
import com.example.homeworks.ui.fourth.PhotoFragment
import com.example.homeworks.ui.second.BiographyFragment
import com.example.homeworks.ui.sixth.FIFAFragment
import com.example.homeworks.ui.third.StatisticFragment
import kotlinx.android.synthetic.main.activity_nav_drawer.*

class NavigationDrawerActivity : AppCompatActivity(), PhotoFragment.OnFourthFragmentMoveListener,
    MemesFragment.OnFifthFragmentMoveListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = drawer_layout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_review -> {
                    supportFragmentManager.also {
                        it.beginTransaction().apply {
                            replace(R.id.container_fragment, ReviewFragment.newInstance())
                            addToBackStack(ReviewFragment::class.java.name)
                            drawer_layout.closeDrawer(GravityCompat.START)
                            commit()
                        }
                    }
                    true
                }
                R.id.nav_biography -> {
                    supportFragmentManager.also {
                        it.beginTransaction().apply {
                            replace(R.id.container_fragment, BiographyFragment.newInstance())
                            addToBackStack(BiographyFragment::class.java.name)
                            drawer_layout.closeDrawer(GravityCompat.START)
                            commit()
                        }
                    }
                    true
                }
                R.id.nav_statistic -> {
                    supportFragmentManager.also {
                        it.beginTransaction().apply {
                            replace(R.id.container_fragment, StatisticFragment.newInstance())
                            addToBackStack(StatisticFragment::class.java.name)
                            drawer_layout.closeDrawer(GravityCompat.START)
                            commit()
                        }
                    }
                    true
                }
                R.id.nav_photo -> {
                    supportFragmentManager.also {
                        it.beginTransaction().apply {
                            replace(R.id.container_fragment, PhotoFragment.newInstance())
                            addToBackStack(PhotoFragment::class.java.name)
                            drawer_layout.closeDrawer(GravityCompat.START)
                            commit()
                        }
                    }
                    true
                }
                else -> {true}
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        true

    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        when {
            drawer.isDrawerOpen(GravityCompat.START) -> drawer.closeDrawer(GravityCompat.START)
            supportFragmentManager.backStackEntryCount > 0 -> supportFragmentManager.popBackStack()
            else -> super.onBackPressed()
        }
    }


    override fun onMoveListenerFromFourth() {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
                replace(R.id.container_fragment, MemesFragment.newInstance())
                addToBackStack(MemesFragment::class.java.name)
                commit()
            }
        }

    }

    override fun onMoveListenerFromFifth(first: String, sec: String, third: String) {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                replace(R.id.container_fragment, FIFAFragment.newInstance(first, sec, third))
                addToBackStack(FIFAFragment::class.java.name)
                commit()
            }
        }

    }
}
