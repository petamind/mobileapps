package com.petamind.numberguessinggame

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast


class BackgroundSoundService : Service() {
    var mediaPlayer: MediaPlayer? = null
    var soundID: Int = R.raw.grasshopper

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        soundID = intent.getIntExtra("id", R.raw.grasshopper)
        mediaPlayer = MediaPlayer.create(this, this.soundID)
        mediaPlayer!!.isLooping = true // Set looping
        mediaPlayer!!.setVolume(100f, 100f)
        mediaPlayer!!.start()
        return startId
    }

    override fun onStart(intent: Intent, startId: Int) {

    }
    override fun onDestroy() {
        mediaPlayer!!.stop()
        mediaPlayer!!.release()
    }

}