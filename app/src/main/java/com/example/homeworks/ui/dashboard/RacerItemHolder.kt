package com.example.homeworks.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_racer.*

class RacerItemHolder (
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {


    fun bind(racer : Racer) {
        tv_name.text = racer.fullName
        tv_team.text = racer.team
    }

    companion object {
        fun create(parent: ViewGroup) =
            RacerItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_racer, parent, false)
            )
    }

}