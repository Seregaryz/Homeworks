package com.example.homeworks.music

import android.media.MediaPlayer
import com.example.homeworks.R

class MusicDAO {

    companion object{
        var musicList =
            getDataSource()
        var curPosition = -1
        var mediaPlayer = MediaPlayer()

        fun getDataSource(): List<Song> = arrayListOf(
            Song(
                "Big in Japan",
                "Alphaville",
                "Platinum",
                R.mipmap.biginjapan,
                R.raw.biginjapan
            ),
            Song(
                "Forever young",
                "Alphaville",
                "Platinum",
                R.mipmap.foreveryoung,
                R.raw.foreveryounng
            ),
            Song(
                "Take on me",
                "A-ha",
                "Platinum",
                R.mipmap.takeonme,
                R.raw.aha_tom
            ),
            Song(
                "Every breath you take",
                "The police",
                "Platinum",
                R.mipmap.everybreathyoutake,
                R.raw.everybreathyoutake
            ),
            Song(
                "Never gonna give you up",
                "Rick Astley",
                "Platinum",
                R.mipmap.nevergonnaguveup,
                R.raw.rick_astley
            ),
            Song(
                "Don't cry tonight",
                "Savage",
                "Platinum",
                R.mipmap.dontcrytonight,
                R.raw.dontcrytonight
            ),
            Song(
                "Only you",
                "Savage",
                "Platinum",
                R.mipmap.onlyu,
                R.raw.onlyu
            ),
            Song(
                "Touch in the night",
                "Silent circle",
                "Platinum",
                R.mipmap.touchinthenight,
                R.raw.touchinthenight
            )
        )
    }
}
