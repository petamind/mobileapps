package com.petamind.ping.components

import android.graphics.Canvas

interface GameLoop {
    var frameRate: Int
    var timeToUpdate: Long
    val shouldUpdate: Boolean
        get() = (System.currentTimeMillis() >= timeToUpdate)

    fun render(canvas: Canvas? = null)
    fun update()
}