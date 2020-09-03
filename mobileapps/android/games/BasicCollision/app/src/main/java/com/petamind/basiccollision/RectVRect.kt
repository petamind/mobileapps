package com.petamind.basiccollision

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View

class RectVRect(context: Context): View(context) {
    var r1: Rect = Rect(0, 0, 200, 300)
    var r2: Rect = Rect(0, 0, 100, 100)
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
        r1.offsetTo(canvas?.width!!/2 - r1.width()/2, canvas?.height/2 - r1.height()/2)
        canvas?.drawRect(r1, p)
        canvas?.drawRect(r2, p)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        r2.offsetTo((event!!.x - r2.width()/2).toInt(), (event!!.y -r2.height()/2).toInt())
        val r2Copy = Rect(r2)
        if(r2Copy.intersect(r1)) {
            p.color = Color.RED
        } else {
            p.color = Color.BLACK
        }


        postInvalidate()
        return true
    }

}