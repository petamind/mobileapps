package com.petamind.ping.components

import android.content.Context
import android.graphics.RectF

class Player(context: Context, shapeId: Int?, isAI: Boolean = false) : Sprite(context, shapeId) {
    var isAI = false
        set(value) {
            field = value
        }

    init {
        this.isAI = isAI
        location = RectF(0f, 0f, 200f, 50f)
        if (isAI) {
            movVec.x = 5f
        }
    }

    override fun collide(o: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override var frameRate: Int = 30
        set(value) {
            field = value
        }
    override var timeToUpdate: Long = System.currentTimeMillis()
        set(value) {
            field = value
        }

    override fun update() {
        if (!shouldUpdate) {
            return
        }
        timeToUpdate += 1000L / frameRate
    }
}