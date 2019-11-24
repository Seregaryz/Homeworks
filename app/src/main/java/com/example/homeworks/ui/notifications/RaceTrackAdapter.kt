package com.example.homeworks.ui.notifications

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RaceTrackAdapter (
    private var dataSource: List<RaceTrack>
    ) : RecyclerView.Adapter<RaceTrackItemHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaceTrackItemHolder =
            RaceTrackItemHolder.create(parent)

        override fun getItemCount(): Int = dataSource.size

        override fun onBindViewHolder(holder: RaceTrackItemHolder, position: Int) =
            holder.bind(dataSource[position])

    }