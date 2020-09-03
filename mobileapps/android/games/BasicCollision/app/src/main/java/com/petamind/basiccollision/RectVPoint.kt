package com.petamind.basiccollision

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View

class RectVPoint(context: Context?) : View(context) {
    var r1: Rect = Rect(0, 0, 200, 300)
    var p: Paint = Paint()

    init {
        p.apply {
            style = Paint.Style.STROKE
            color = Color.BLACK
            strokeWidth = 5f
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(r1, p)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(r1.contains(event!!.x.toInt(), event!!.y.toInt())) {
            p.color = Color.RED
            r1.offsetTo((event!!.x - r1.width()/2).toInt(), ((event!!.y -r1.height()/2).toInt()))
        } else {
            p.color = Color.BLACK
        }
        postInvalidate()
        return true
    }
}