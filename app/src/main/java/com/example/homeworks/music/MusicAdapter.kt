package com.example.homeworks.music

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter (
    private var dataSource: List<Song>,
    private val clickLambda: (Song) -> Unit
) : RecyclerView.Adapter<MusicItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicItemHolder =
        MusicItemHolder.create(
            parent,
            clickLambda
        )

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: MusicItemHolder, position: Int) =
        holder.bind(dataSource[position])
}