package com.example.homeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworks.music.MusicAdapter
import com.example.homeworks.music.MusicDAO.Companion.musicList
import com.example.homeworks.music.Song
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapter: MusicAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = MusicAdapter(musicList) { music ->
            navigateToPlayer(music)
        }
        rv_music.adapter = adapter
    }

    private fun navigateToPlayer(song: Song) {
        startActivity(PlayerActivity.createIntent(this, this, song, musicList.indexOf(song)))
    }
}
