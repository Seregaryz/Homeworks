package com.example.homeworks

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.example.homeworks.Constans.Companion.EXTRA_ICON
import com.example.homeworks.Constans.Companion.EXTRA_POSITION
import com.example.homeworks.Constans.Companion.EXTRA_SONG
import com.example.homeworks.music.MusicDAO.Companion.curPosition
import com.example.homeworks.music.MusicDAO.Companion.mediaPlayer
import com.example.homeworks.music.MusicDAO.Companion.musicList

class MusicService : Service() {

    private val mBinder: IBinder = LocalBinder()
    private var startId: Int = 1

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val myNotificationManager = MyNotificationManager()
        when (intent?.action) {
            Constans.PAUSE_ACTION -> {
                pause()
                myNotificationManager.showNotification(notificationManager, this, musicList[curPosition])
            }
            Constans.NEXT_SONG_ACTION -> {
                nextSong(this)
                myNotificationManager.showNotification(notificationManager, this, musicList[curPosition])
            }
            Constans.PREV_ACTION -> {
                previousSong(this)
                myNotificationManager.showNotification(notificationManager, this, musicList[curPosition])
            }
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        val position = intent?.extras?.getInt(EXTRA_POSITION) ?: 1
        val music = intent?.extras?.getInt(EXTRA_SONG) ?: 1
        intent?.putExtra(EXTRA_ICON, R.drawable.ic_pause)
        startSong(music, position)
        startId++
        return mBinder
    }

    fun startSong(music: Int, position: Int){
        if (curPosition != position) {
            mediaPlayer.release()
            mediaPlayer = MediaPlayer.create(this, music)
            mediaPlayer.start()
            curPosition = position
        }
    }

    fun nextSong(context: Context) {
        mediaPlayer.release()
        if(curPosition < musicList.size - 1){
            curPosition++
            mediaPlayer = MediaPlayer.create(context, musicList[curPosition].music)
        } else {
            curPosition = 0
            mediaPlayer = MediaPlayer.create(context, musicList[curPosition].music)
        }
        mediaPlayer.start()
    }

    fun previousSong(context: Context) {
        mediaPlayer.release()
        if (curPosition > 0) {
            curPosition--
            mediaPlayer = MediaPlayer.create(context, musicList[curPosition].music)
        } else {
            curPosition = musicList.size - 1
            mediaPlayer = MediaPlayer.create(context, musicList[curPosition].music)
        }
        mediaPlayer.start()
    }

    fun pause() {
        if (mediaPlayer.isPlaying) mediaPlayer.pause()
        else mediaPlayer.start()
    }

    class LocalBinder : Binder() {
        fun getService() = MusicService()
    }
}
