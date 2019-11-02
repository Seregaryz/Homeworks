package com.example.homeworks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_racer.*

class RacerItemHolder (
    override val containerView: View,
    private val clickLambda: (String, Int, String, Int, Int, Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(racer : Racer) {

        tv_name.text = racer.fullName
        tv_team.text = racer.team
        img_racer.setImageResource(racer.imageId)

        itemView.setOnClickListener {
            clickLambda(racer.fullName, racer.age, racer.team,
                racer.granPrix, racer.points, racer.imageId)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (String, Int, String, Int, Int, Int) -> Unit) =
            RacerItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_racer, parent, false),
                clickLambda
            )
    }

}
