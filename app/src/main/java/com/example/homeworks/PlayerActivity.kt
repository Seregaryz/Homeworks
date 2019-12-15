package com.example.homeworks

import android.app.Activity
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder
import com.example.homeworks.Constans.Companion.EXTRA_ALBUM
import com.example.homeworks.Constans.Companion.EXTRA_MUSIC
import com.example.homeworks.Constans.Companion.EXTRA_NOTIFICATION_SINGER
import com.example.homeworks.Constans.Companion.EXTRA_NOTIFICATION_TITLE
import com.example.homeworks.Constans.Companion.EXTRA_POSITION
import com.example.homeworks.Constans.Companion.EXTRA_POSTER
import com.example.homeworks.Constans.Companion.EXTRA_SINGER
import com.example.homeworks.Constans.Companion.EXTRA_SONG
import com.example.homeworks.Constans.Companion.EXTRA_TITLE
import com.example.homeworks.music.MusicDAO
import com.example.homeworks.music.MusicDAO.Companion.curPosition
import com.example.homeworks.music.MusicDAO.Companion.mediaPlayer
import com.example.homeworks.music.MusicDAO.Companion.musicList
import com.example.homeworks.music.Song
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    private var musicService: MusicService? = null
    private var mBound : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val title = intent?.extras?.getString(EXTRA_TITLE) ?: "Unknown"
        val singer = intent?.extras?.getString(EXTRA_SINGER) ?: "Unknown"
        val album = intent?.extras?.getString(EXTRA_ALBUM) ?: "Unknown"
        val poster = intent?.extras?.getInt(EXTRA_POSTER) ?: R.mipmap.kvyat
        val music = intent?.extras?.getInt(EXTRA_MUSIC) ?: 1
        val song = Song(title, singer, album, poster, music)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val myNotificationManager = MyNotificationManager()
        tv_title.text = title
        tv_singer.text = singer
        tv_album.text = album
        img_music.setImageResource(poster)
        if(mediaPlayer.isPlaying) im_btn_play.setImageResource(R.drawable.pause)
        else im_btn_play.setImageResource(R.drawable.ic_play48)

        im_btn_play.setOnClickListener {
            if(mediaPlayer.isPlaying) im_btn_play.setImageResource(R.drawable.ic_play48)
            else im_btn_play.setImageResource(R.drawable.pause)
            musicService?.pause()
            myNotificationManager.showNotification(notificationManager, this, song)
        }

        im_btn_next.setOnClickListener {
            musicService?.nextSong(this)
            startActivity(createIntent(this, this, MusicDAO.musicList[curPosition], curPosition))
            myNotificationManager.showNotification(notificationManager, this, musicList[curPosition])
        }

        im_btn_previous.setOnClickListener {
            musicService?.previousSong(this)
            startActivity(createIntent(this, this, MusicDAO.musicList[curPosition], curPosition))
            myNotificationManager.showNotification(notificationManager, this, musicList[curPosition])
        }
    }

    override fun onStart() {
        super.onStart()
        val position = intent?.extras?.getInt(EXTRA_POSITION) ?: 1
        val music = intent?.extras?.getInt(EXTRA_MUSIC) ?: 1
        val title = intent?.extras?.getString(EXTRA_TITLE) ?: "Unknown"
        val album = intent?.extras?.getString(EXTRA_ALBUM) ?: "Unknown"
        val poster = intent?.extras?.getInt(EXTRA_POSTER) ?: R.mipmap.kvyat
        val singer = intent?.extras?.getString(EXTRA_SINGER) ?: "Unknown"
        tv_title.text = title
        tv_singer.text = singer
        tv_album.text = album
        img_music.setImageResource(poster)
        val song = Song(album, singer, album, poster, music)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val myNotificationManager = MyNotificationManager()
        myNotificationManager.showNotification(notificationManager, this, song)
        val bindingIntent = Intent(this, MusicService::class.java)
        bindingIntent.putExtra(EXTRA_SONG, music)
        bindingIntent.putExtra(EXTRA_POSITION, position)
        bindingIntent.putExtra(EXTRA_NOTIFICATION_TITLE, title)
        bindingIntent.putExtra(EXTRA_NOTIFICATION_SINGER, singer)
        bindService(bindingIntent, playerConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(playerConnection)
    }

    override fun onResume() {
        super.onResume()
        if(mediaPlayer.isPlaying) im_btn_play.setImageResource(R.drawable.pause)
        else im_btn_play.setImageResource(R.drawable.ic_play48)
    }

    private val playerConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as MusicService.LocalBinder
            musicService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    companion object {
        fun createIntent(activity: Activity, context: Context, song: Song, position: Int): Intent {
            val intent = Intent(activity, PlayerActivity::class.java).apply {
                putExtra(EXTRA_TITLE, song.title)
                putExtra(EXTRA_SINGER, song.singer)
                putExtra(EXTRA_ALBUM, song.album)
                putExtra(EXTRA_MUSIC, song.music)
                putExtra(EXTRA_POSTER, song.poster)
                putExtra(EXTRA_POSITION, position)
            }
            TaskStackBuilder.create(context).apply {
                addParentStack(MainActivity::class.java)
                addNextIntent(intent)
            }
            return intent
        }
    }
}

