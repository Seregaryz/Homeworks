package com.example.homeworks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_racer.*

class RacerItemHolder (
    override val containerView: View,
    private val clickLambda: (Racer) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(racer : Racer) {

        tv_name.text = racer.fullName
        tv_team.text = racer.team
        img_racer.setImageResource(racer.imageId)

        itemView.setOnClickListener {
            clickLambda(racer)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (Racer) -> Unit) =
            RacerItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_racer, parent, false),
                clickLambda
            )
    }

}
