package com.petamind.basiccollision

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View

class RectVCircle(context: Context?) : View(context) {
    var r1: Rect = Rect(0, 0, 200, 300)
    var p: Paint = Paint()
    class Circle {
        var center = PointF()
        var radius = 50f
    }
    var c1: Circle = Circle()

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
        canvas?.drawCircle(c1.center.x, c1.center.y, c1.radius, p)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        c1.center.set(event!!.x, event.y)
        collisionDectection()
        postInvalidate()
        return true
    }

    private fun collisionDectection() {
        var collided = false
        //1 the center of the circle is inside the rect 1
        if(r1.contains(c1.center.x.toInt(), c1.center.y.toInt())){
            collided = true
        } else {
            var pEdge = PointF()
            if(c1.center.x < r1.left) {
                pEdge.x = r1.left.toFloat()
            } else if(c1.center.x > r1.right) {
                pEdge.x = r1.right.toFloat()
            } else {
                pEdge.x = c1.center.x
            }

            if(c1.center.y < r1.top) {
                pEdge.y = r1.top.toFloat()
            } else if(c1.center.y > r1.bottom) {
                pEdge.y = r1.bottom.toFloat()
            } else{
                pEdge.y = c1.center.y
            }

            val deltaX = c1.center.x - pEdge.x
            val deltaY = c1.center.y - pEdge.y
            val distance = Math.sqrt((deltaX*deltaX + deltaY*deltaY).toDouble())
            collided = (distance <= c1.radius)
        }

        if(collided) {
            p.color = Color.RED
        } else {
            p.color = Color.BLACK
        }
    }
}