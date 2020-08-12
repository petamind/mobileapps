package com.petamind.superstickey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Please, stay with me!", Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        restart()
        super.onPause()
    }

    override fun onDestroy() {
        restart()
        super.onDestroy()
    }

    fun restart() {
        Toast.makeText(this,"See you in 1 sec! :D", Toast.LENGTH_LONG).show()
        val intent = Intent(this, MainActivity::class.java)
        Handler().postDelayed(Runnable {
            startActivity(intent)
        }, 20)
    }
}

