package com.example.homeworks.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_cardview.*

class RaceTrackItemHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(raceTrack: RaceTrack) {
        cv_name.text = raceTrack.name
        cv_desc.text = raceTrack.description
    }

    companion object {
        fun create(parent: ViewGroup) =
            RaceTrackItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
            )
    }
}