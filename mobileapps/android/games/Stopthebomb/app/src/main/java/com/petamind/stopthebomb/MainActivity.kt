package com.petamind.stopthebomb

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AnimationListener, View.OnClickListener {
    lateinit var anim: ProgressBarAnimation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(android.R.id.content).setOnClickListener(this)
        val animation = AnimationUtils.loadAnimation(this, R.anim.zoom)
        image.startAnimation(animation)
    }

    private fun reset() {
        startMusic(R.raw.grasshopper)
        image.setImageResource(R.drawable.ic_bomb)
        progress.setScaleY(3f);
        progress.progress = 0

        anim = ProgressBarAnimation(progress, 0f, 100f)
        anim.setAnimationListener(this)
        anim.duration = 2000
        button.isEnabled = true
    }

    private fun startMusic(id: Int = R.raw.maze) {
        stopMusic()
        startService(Intent(this, BackgroundSoundService::class.java).apply {
            putExtra("id", id)
        })
    }

    private fun stopMusic() {
        stopService(Intent(this, BackgroundSoundService::class.java))
    }

    fun start(v: View) {
        reset()
        startMusic()
        button.isEnabled = false
        progress.startAnimation(anim)
    }

    override fun onResume() {
        super.onResume()
        reset()
    }

    override fun onPause() {
        super.onPause()
        stopMusic()
    }

    override fun onAnimationRepeat(p0: Animation?) {

    }

    override fun onAnimationEnd(p0: Animation?) {
        if (progress.progress == progress.max) {
            Toast.makeText(this, "OMG! You did it!", Toast.LENGTH_LONG).show()
            image.setImageResource(R.drawable.ic_explode)
            startMusic(R.raw.explosion_1)
            progress.setOnClickListener(null)

        } else {
            Toast.makeText(this, "Woohoo! You finished it!", Toast.LENGTH_LONG).show()
            image.setImageResource(R.drawable.ic_melon)
            startMusic(R.raw.wow)
        }
        Handler().postDelayed({reset()}, 10000)
    }

    override fun onAnimationStart(p0: Animation?) {

    }

    override fun onClick(p0: View?) {
        progress.progress -= 1
        if (progress.progress <= 0) {
            progress.clearAnimation()
        } else {
            anim.from = progress.progress.toFloat()
            progress.startAnimation(anim)
        }
    }
}