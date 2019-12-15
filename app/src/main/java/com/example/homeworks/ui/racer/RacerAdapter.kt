package com.example.homeworks.ui.racer

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RacerAdapter(
    private var dataSource: ArrayList<Racer>,
    private val clickLambda: (Racer) -> Unit
) : RecyclerView.Adapter<RacerItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RacerItemHolder =
        RacerItemHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: RacerItemHolder, position: Int) =
        holder.bind(dataSource[position])

    fun addNewRacer(racer: Racer, position: Int){
        dataSource.add(position, racer)
    }

    fun updateList(newList: ArrayList<Racer>) {
        androidx.recyclerview.widget.DiffUtil.calculateDiff(
            DiffUtil(this.dataSource, newList),
            true
        )
            .dispatchUpdatesTo(this)
        this.dataSource.clear()
        this.dataSource.addAll(newList)
    }

}
