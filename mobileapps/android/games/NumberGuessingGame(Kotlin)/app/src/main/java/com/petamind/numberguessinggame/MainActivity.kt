package com.petamind.numberguessinggame

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    val fadein: Animation
        get() = AnimationUtils.loadAnimation(this, R.anim.fadein)
    val blink: Animation
        get() = AnimationUtils.loadAnimation(this, R.anim.blink)
    val shake: Animation
        get() = AnimationUtils.loadAnimation(this, R.anim.shake)
    val wobble: Animation
        get() = AnimationUtils.loadAnimation(this, R.anim.wobble)
    val slideinleft: Animation
        get() = AnimationUtils.loadAnimation(this, R.anim.slideinleft)
    val slideinright: Animation
        get() = AnimationUtils.loadAnimation(this, R.anim.slideinright)
    val slideout: Animation
        get() = AnimationUtils.loadAnimation(this, R.anim.slideoutright)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
            image.startAnimation(this.fadein)
            playBtn.startAnimation(slideinleft)
            titleTV.startAnimation(this.wobble)
            introBtn.startAnimation(slideinright)
            //playBtn.startAnimation(this.blink)

        } else {
            playBtn.startAnimation(slideout)
            introBtn.startAnimation(slideout)
        }
    }

    override fun onResume() {
        super.onResume()
        startService(Intent(this, BackgroundSoundService::class.java).apply {
            putExtra("id", R.raw.grasshopper)
        })
    }

    override fun onPause() {
        super.onPause()
        stopService(Intent(this, BackgroundSoundService::class.java))
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    override fun onClick(v: View?) {
        when(v){
            playBtn -> startActivity(Intent(this, GameActivity::class.java))
        }
    }
}