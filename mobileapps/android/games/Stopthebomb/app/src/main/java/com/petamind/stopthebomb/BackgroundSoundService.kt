package com.petamind.stopthebomb

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder


class BackgroundSoundService : Service() {
    var mediaPlayer: MediaPlayer? = null
    var soundID: Int = R.raw.grasshopper

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        soundID = intent.getIntExtra("id", R.raw.grasshopper)
        mediaPlayer = MediaPlayer.create(this, this.soundID)
        mediaPlayer!!.isLooping = false // Set looping
        mediaPlayer!!.setVolume(100f, 100f)
        mediaPlayer!!.start()
        return startId
    }

    override fun onDestroy() {
        mediaPlayer!!.stop()
        mediaPlayer!!.release()
    }
}