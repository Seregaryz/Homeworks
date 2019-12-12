package com.example.homeworks

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.example.homeworks.music.MusicDAO
import com.example.homeworks.music.Song

class MusicService : Service() {


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val music = intent?.extras?.getInt(EXTRA_SONG)?: 1
        val position = intent?.extras?.getInt(EXTRA_START_ACTION)?: 1

        when(intent?.action){
            Constans.PAUSE_ACTION -> {
                pause()
            }
            Constans.NEXT_SONG_ACTION -> {
                nextSong()
            }
            Constans.PREV_ACTION -> {
                previousSong()
            }
            Constans.INIT_ACTION -> {
                if(MusicDAO.curPosition != position){
                    MusicDAO.mediaPlayer.release()
                    MusicDAO.mediaPlayer = MediaPlayer.create(this, music)
                    MusicDAO.mediaPlayer.start()
                    MusicDAO.curPosition = position
                }
                val contextPendingIntent = MyNotificationManager.createPendingIntent(this)
                val pausePendingIntent = MyNotificationManager.createPausePendingIntent(this)
                val prevPendingIntent = MyNotificationManager.createPreviousSongPendingIntent(this)
                val nextPendingIntent = MyNotificationManager.createNextSongPendingIntent(this)
                startForeground(1, MyNotificationManager.createPlayerNotification(this, "1",
                    contextPendingIntent, prevPendingIntent, pausePendingIntent, nextPendingIntent))
            }
        }
        return START_NOT_STICKY

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun nextSong(){
        MusicDAO.mediaPlayer.release()
        if(MusicDAO.curPosition < MusicDAO.musicList.size - 1){
            MusicDAO.curPosition++
            MusicDAO.mediaPlayer = MediaPlayer.create(this, MusicDAO.musicList[MusicDAO.curPosition].music)
        } else {
            MusicDAO.curPosition = 0
            MusicDAO.mediaPlayer = MediaPlayer.create(this, MusicDAO.musicList[MusicDAO.curPosition].music)
        }
        MusicDAO.mediaPlayer.start()
    }

    private fun previousSong(){
        MusicDAO.mediaPlayer.release()
        if(MusicDAO.curPosition > 0){
            MusicDAO.curPosition--
            MusicDAO.mediaPlayer = MediaPlayer.create(this, MusicDAO.musicList[MusicDAO.curPosition].music)
        } else {
            MusicDAO.curPosition = MusicDAO.musicList.size - 1
            MusicDAO.mediaPlayer = MediaPlayer.create(this, MusicDAO.musicList[MusicDAO.curPosition].music)
        }
        MusicDAO.mediaPlayer.start()
    }

    private fun pause(){
        if(MusicDAO.mediaPlayer.isPlaying) {
            MusicDAO.mediaPlayer.pause()
        } else{ MusicDAO.mediaPlayer.start()}
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = R.string.music_channel_name.toString()
            val descriptionText = R.string.music_channel_description.toString()
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)


        }
    }

    companion object {

        private const val EXTRA_START_ACTION = "ACTION"
        private const val EXTRA_SONG = "SONG"

        fun startService(context: Context, position: Int, song: Song) {
            val startIntent = Intent(context, MusicService::class.java)
            startIntent.action = Constans.INIT_ACTION
            startIntent.putExtra(EXTRA_START_ACTION, position)
            startIntent.putExtra(EXTRA_SONG, song.music)
            ContextCompat.startForegroundService(context, startIntent)
        }

        fun stopService(context: Context) {
            val stopIntent = Intent(context, MusicService::class.java)
            context.stopService(stopIntent)
        }

    }


}