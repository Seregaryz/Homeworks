package com.example.homeworks

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.example.homeworks.music.MusicDAO.Companion.curPosition
import com.example.homeworks.music.MusicDAO.Companion.mediaPlayer
import com.example.homeworks.music.Song

class MyNotificationManager {

    private var notificationId : Int = 1

    fun createPendingIntent(context: Context, song: Song): PendingIntent {
        val intent = Intent(context, PlayerActivity::class.java)
        intent.putExtra(Constans.EXTRA_TITLE, song.title)
        intent.putExtra(Constans.EXTRA_SINGER, song.singer)
        intent.putExtra(Constans.EXTRA_ALBUM, song.album)
        intent.putExtra(Constans.EXTRA_MUSIC, song.music)
        intent.putExtra(Constans.EXTRA_POSTER, song.poster)
        intent.putExtra(Constans.EXTRA_POSITION, curPosition)
        TaskStackBuilder.create(context).apply {
            addParentStack(MainActivity::class.java)
            addNextIntent(intent)
        }
        return PendingIntent.getActivity(context, 0, intent, 0)
    }

    fun showNotification(notificationManager: NotificationManager, context: Context, song: Song){
        createNotificationChannel(notificationManager)
        val contentPendingIntent = createPendingIntent(context, song)
        val pausePendingIntent = createPausePendingIntent(context)
        val prevPendingIntent = createPreviousSongPendingIntent(context)
        val nextPendingIntent = createNextSongPendingIntent(context)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle())
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(song.title)
            .setContentText(song.singer)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentIntent(contentPendingIntent)
            .addAction(R.drawable.ic_back, "Previous", prevPendingIntent)

        if(mediaPlayer.isPlaying) {
            builder.addAction(R.drawable.ic_pause, "Pause", pausePendingIntent)
            builder.addAction(R.drawable.ic_next, "Next", nextPendingIntent)
        } else {
            builder.addAction(R.drawable.ic_play, "Pause", pausePendingIntent)
            builder.addAction(R.drawable.ic_next, "Next", nextPendingIntent)
        }
        val notification = builder.build()
        notificationManager.notify(notificationId, notification)
    }

    fun createPausePendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MusicService::class.java)
        intent.action = Constans.PAUSE_ACTION
        return PendingIntent.getService(context, 0, intent, 0)
    }

    fun createNextSongPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MusicService::class.java)
        intent.action = Constans.NEXT_SONG_ACTION
        return PendingIntent.getService(context, 0, intent, 0)
    }

    fun createPreviousSongPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MusicService::class.java)
        intent.action = Constans.PREV_ACTION
        return PendingIntent.getService(context, 0, intent, 0)
    }

    fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = R.string.music_channel_name.toString()
            val descriptionText = R.string.music_channel_description.toString()
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = notificationManager.getNotificationChannel(CHANNEL_ID)
                ?: NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object{
        private const val CHANNEL_ID = "1"
    }
}
