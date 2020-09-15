package com.petamind.ping.components

import android.content.Context

open class Player(context: Context, shapeId: Int?) : Sprite(context, shapeId) {
    var score = 0

    override var updateRate: Int = 1
    override var timeToUpdate: Long = System.currentTimeMillis()

    override fun update() {
        if (shouldUpdate) {
            timeToUpdate = System.currentTimeMillis() + 1000L / updateRate
        }
    }
}