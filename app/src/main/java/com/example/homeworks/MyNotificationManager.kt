package com.example.homeworks

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder


class MyNotificationManager {

    companion object {


        fun createPendingIntent(context: Context) : PendingIntent{
            val intent = Intent(context, PlayerActivity::class.java)
            TaskStackBuilder.create(context).apply {
                addParentStack(MainActivity::class.java)
                addNextIntent(intent)
            }
            return PendingIntent.getActivity(
                context,
                0,
                intent,
                0
            )
        }


        fun createPlayerNotification(context: Context, channelId: String, contentIntent: PendingIntent, prevIntent: PendingIntent,
                                     pauseIntent: PendingIntent, nextIntent: PendingIntent) =
            NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My player")
                .setContentText("Text")
                .setContentIntent(contentIntent)
                .addAction(R.drawable.back, "Previous", prevIntent)
                .addAction(R.drawable.play, "Pause", pauseIntent)
                .addAction(R.drawable.next, "Next", nextIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()




        fun createPausePendingIntent(context: Context) : PendingIntent{
            val intent = Intent(context, MusicService::class.java)
            intent.action = Constans.PAUSE_ACTION
            return PendingIntent.getService(context, 0, intent, 0)
        }

        fun createNextSongPendingIntent(context: Context) : PendingIntent{
            val intent = Intent(context, MusicService::class.java)
            intent.action = Constans.NEXT_SONG_ACTION
            return PendingIntent.getService(context, 0, intent, 0)
        }

        fun createPreviousSongPendingIntent(context: Context) : PendingIntent {
            val intent = Intent(context, MusicService::class.java)
            intent.action = Constans.PREV_ACTION
            return PendingIntent.getService(context, 0, intent, 0)

        }
    }



}
