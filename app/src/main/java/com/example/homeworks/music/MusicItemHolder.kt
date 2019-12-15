package com.example.homeworks.music

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_music.*

class MusicItemHolder  (
    override val containerView: View,
    private val clickLambda: (Song) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(song : Song) {
        val singer_album = song.singer + " (" + song.album + ")"
        tv_title.text = song.title
        tv_album.text = singer_album
        img_music.setImageResource(song.poster)
        itemView.setOnClickListener {
            clickLambda(song)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Song) -> Unit) =
            MusicItemHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_music,
                    parent,
                    false
                ),
                clickLambda
            )
    }
}

