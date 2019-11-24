package com.example.homeworks

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RacerAdapter(
    private var dataSource: List<Racer>,
    private val clickLambda: (Racer) -> Unit
) : RecyclerView.Adapter<RacerItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RacerItemHolder =
        RacerItemHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: RacerItemHolder, position: Int) =
        holder.bind(dataSource[position])

}
