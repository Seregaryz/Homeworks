package com.example.homeworks.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homeworks.R
import com.example.homeworks.interfaces.OnFragmentInteractionListener
import com.example.homeworks.list.fragments.ListFragment

class ListActivity : AppCompatActivity(), OnFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fr_container, ListFragment.newInstance())
            commit()
        }

    }

    override fun onFragmentInteraction(fragment: Fragment){
        supportFragmentManager.also {
            it.beginTransaction().apply {
                replace(R.id.fr_container, fragment)
                addToBackStack(fragment::class.java.name)
                commit()
            }
        }
    }
}
