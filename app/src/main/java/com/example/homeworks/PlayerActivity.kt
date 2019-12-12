package com.example.homeworks

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworks.music.MusicDAO
import com.example.homeworks.music.MusicDAO.Companion.curPosition
import com.example.homeworks.music.MusicDAO.Companion.mediaPlayer
import com.example.homeworks.music.Song
import kotlinx.android.synthetic.main.activity_player.*


class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val title = intent?.extras?.getString(EXTRA_TITLE) ?: "Unknown"
        val singer = intent?.extras?.getString(EXTRA_SINGER) ?: "Unknown"
        val album = intent?.extras?.getString(EXTRA_ALBUM) ?: "Unknown"
        val poster = intent?.extras?.getInt(EXTRA_POSTER) ?: R.mipmap.kvyat

        tv_title.text = title
        tv_singer.text = singer
        tv_singer.text = album
        img_music.setImageResource(poster)


        btn_play.setOnClickListener {
            if(!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        btn_pause.setOnClickListener {
            mediaPlayer.pause()
        }

        btn_skip_to_next.setOnClickListener {
            mediaPlayer.release()
            if(curPosition < MusicDAO.musicList.size - 1){
                curPosition++
                mediaPlayer = MediaPlayer.create(this, MusicDAO.musicList[curPosition].music)
            } else {
                curPosition = 0
                mediaPlayer = MediaPlayer.create(this, MusicDAO.musicList[curPosition].music)
            }
            mediaPlayer.start()
        }

        btn_skip_to_previous.setOnClickListener {
            mediaPlayer.release()
            if(curPosition > 0){
                curPosition--
                mediaPlayer = MediaPlayer.create(this, MusicDAO.musicList[curPosition].music)
            } else {
                curPosition = MusicDAO.musicList.size - 1
                mediaPlayer = MediaPlayer.create(this, MusicDAO.musicList[curPosition].music)
            }
            mediaPlayer.start()
        }

    }

    companion object {

        private const val EXTRA_TITLE = "title"
        private const val EXTRA_SINGER = "singer"
        private const val EXTRA_ALBUM = "album"
        private const val EXTRA_MUSIC = "music"
        private const val EXTRA_POSTER = "poster"

        fun createIntent(activity: Activity, song: Song) =
            Intent(activity, PlayerActivity::class.java).apply {
                putExtra(EXTRA_TITLE, song.title)
                putExtra(EXTRA_SINGER, song.singer)
                putExtra(EXTRA_ALBUM, song.album)
                putExtra(EXTRA_MUSIC, song.music)
                putExtra(EXTRA_POSTER, song.poster)
            }
    }
}
