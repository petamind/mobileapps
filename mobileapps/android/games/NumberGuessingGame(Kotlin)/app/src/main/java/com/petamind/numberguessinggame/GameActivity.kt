package com.petamind.numberguessinggame

import androidx.appcompat.app.AppCompatActivity
import android.app.AlertDialog
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class GameActivity : AppCompatActivity(), View.OnClickListener {
    private var soundPool: SoundPool? = null
    private var wrongID = -1
    private var correctID = -1
    private var secret: Int = -1
    private var tries = 7
    private val timeLimit = 30000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 100)
        wrongID = soundPool!!.load(baseContext, R.raw.buzzer, 1)
        correctID = soundPool!!.load(baseContext, R.raw.coffin, 1)
        countdown.setOnCountdownEndListener {
            tries = 0
            checkAnswer()
            stopService(Intent(this, BackgroundSoundService::class.java))
        }
        secret = Random.nextInt(100)
        editText.setOnFocusChangeListener(
            { view, b -> if (!b) checkAnswer() }
        )
    }

    override fun onResume() {
        super.onResume()
        startService(Intent(this, BackgroundSoundService::class.java).apply {
            putExtra("id", R.raw.maze)
        })
        countdown.start(timeLimit)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
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
        checkAnswer()
    }

    private fun checkAnswer() {
        if ((editText.text.toString().toInt()) == secret) {
            displayMessage(true)
            soundPool?.play(correctID, 1f,1f,1, 0, 1.0f)
        } else {
            tries--
            displayMessage(false)
            soundPool?.play(wrongID, 1f,1f,1, 0, 1.0f)
        }
    }

    /**
     * @param correct: correct
     */
    private fun displayMessage(correct: Boolean) {
        val mDialogView = LayoutInflater.from(this)
            .inflate(if (!correct) R.layout.wrong_dialog else R.layout.correct_dialog, null)
        // 2. Chain together various setter methods to set the dialog characteristics
        val message =
            if (correct) "Well done!!" else if (tries > 0) "$tries  attempts left" else
                "YOU LOOOSE!"
        val title = if (correct) "Correct!" else "Wrong!!!! " +
                if ((editText.text.toString().toInt()) < secret) "Too small!" else "Too BIG!"
        mDialogView.findViewById<TextView>(R.id.dialog_title).text = title
        mDialogView.findViewById<TextView>(R.id.dialog_message).text = message

        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        val builder: AlertDialog.Builder? = this?.let {
            AlertDialog.Builder(it).setView(mDialogView)
        }


        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        val dialog: AlertDialog? = builder?.create()
        mDialogView.setOnClickListener( {dialog?.dismiss()})
        dialog?.show()
    }

}