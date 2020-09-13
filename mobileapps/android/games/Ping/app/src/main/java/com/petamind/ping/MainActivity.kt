package com.petamind.ping

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petamind.ping.components.GameView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(GameView(this))
    }
}