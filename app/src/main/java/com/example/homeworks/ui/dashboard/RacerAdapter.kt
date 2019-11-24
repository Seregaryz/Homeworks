package com.example.homeworks.ui.dashboard

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RacerAdapter(
    private var dataSource: ArrayList<Racer>
) : RecyclerView.Adapter<RacerItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RacerItemHolder =
        RacerItemHolder.create(parent)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: RacerItemHolder, position: Int) =
        holder.bind(dataSource[position])

    fun addNewRacer(racer: Racer, position: Int){
        dataSource.add(position, racer)
    }

}