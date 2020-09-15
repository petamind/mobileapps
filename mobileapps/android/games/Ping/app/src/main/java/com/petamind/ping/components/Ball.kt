package com.petamind.ping.components

import android.content.Context
import java.lang.System.currentTimeMillis

class Ball(context: Context, shapeId: Int?) : Sprite(context, shapeId) {
    var ratio: Float = 1f


    override var updateRate: Int = 60
    override var timeToUpdate: Long = currentTimeMillis()

    init {
        this.movVec.set(6f, 8f)
    }

    override fun update() {
        if (shouldUpdate) {
            val current = currentTimeMillis()
            val delta = current - timeToUpdate
            ratio = 1f + delta.toFloat() * updateRate / 1000f
            timeToUpdate = current + 1000L / updateRate
            location.offset(
                movVec.x * ratio,
                movVec.y * ratio
            )
        }
    }

}