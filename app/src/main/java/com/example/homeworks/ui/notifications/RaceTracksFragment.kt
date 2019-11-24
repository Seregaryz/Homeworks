package com.example.homeworks.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homeworks.R

class RaceTracksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_racetraks, container, false)

    companion object{
        fun newInstance(): RaceTracksFragment = RaceTracksFragment()
    }

    fun getDataSource(): List<RaceTrack> = arrayListOf(
        RaceTrack("Sochi Authodrom", "great track")
    )
}