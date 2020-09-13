package com.petamind.ping.components

import android.content.Context
import android.graphics.Rect
import java.lang.System.currentTimeMillis

class Ball(context: Context, shapeId: Int?) : Sprite(context, shapeId) {
    var ratio: Float = 1f
    override fun collide(o: Any?): Boolean {
        var endgame = false
        if (o is Rect) {
            if (location.left <= 0 || location.right >= o.right) {
                movVec.x = -movVec.x
                if (location.left <= 0) {
                    location.offset(-location.left, 0f)
                } else {
                    location.offset(o.right - location.right, 0f)
                }

            }

            if (location.top <= 0 || location.bottom >= o.bottom) {
                movVec.y = -movVec.y
                if (location.top <= 0) {
                    location.offset(0f, -location.top)
                } else {
                    location.offset(0f, o.bottom - location.bottom)
                }
                endgame = true
            }

            //if (collided) location.offset(movVec.x*ratio, movVec.y*ratio)
        }

        if (o is Player) {
            if (o.location.contains(location.centerX(), location.bottom)) {
                movVec.y = -movVec.y
                location.offset(0f, o.location.top - location.bottom)
            } else if (o.location.contains(location.centerX(), location.top)
            ) {
                movVec.y = -movVec.y
                location.offset(0f, o.location.bottom - location.top)
            }
        }

        return endgame
    }

    override var frameRate: Int = 60
        set(value) {
            field = value
        }
    override var timeToUpdate: Long = currentTimeMillis()
        set(value) {
            field = value
        }

    init {
        this.movVec.set(6f, 8f)
        //Thread(this).start()
    }

    override fun update() {
        if (shouldUpdate) {
            val current = currentTimeMillis()
            val delta = current - timeToUpdate;
            ratio = 1f + delta.toFloat() * frameRate / 1000f
            timeToUpdate = current + 1000L / frameRate
            //location.offset(movVec.x ,movVec.y )
            //Log.d("render", "$delta : $ratio" )
            location.offset(
                movVec.x * ratio,
                movVec.y * ratio
            )
        }
    }

//    override fun run() {
//        while(true) {
//            if(shouldUpdate){
//                update()
//            }
//        }
//    }
}