package com.petamind.ping.components

import android.content.Context
import android.graphics.*

abstract class Sprite : GameLoop {
    constructor(context: Context, shapeId: Int?) {
        this.context = context
        this.shape = shapeId?.let { BitmapFactory.decodeResource(context.resources, it) }
        if (shape != null) {
            location.right = shape!!.width.toFloat()
            location.bottom = shape!!.height.toFloat()
        }
    }

    var movVec = PointF()
    var paint = Paint()
    var context: Context? = null
    var location = RectF(0f, 0f, 80f, 80f)
    var shape: Bitmap? = null
    override fun render(canvas: Canvas?) {
        if (shape != null) {
            canvas?.drawBitmap(shape!!, null, location, paint)
        } else {
            canvas?.drawRect(location, paint)
        }
    }
}