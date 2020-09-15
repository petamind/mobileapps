package com.petamind.ping.components

import android.content.Context
import java.lang.System.currentTimeMillis

class Ball(context: Context, shapeId: Int?) : Sprite(context, shapeId) {
    var ratio: Float = 1f


    override var updateRate: Int = 100
    override var timeToUpdate: Long = currentTimeMillis()

    init {
        this.movVec.set(3f, 4f)
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