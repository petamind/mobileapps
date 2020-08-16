package com.petamind.stopthebomb

import android.graphics.Color
import android.graphics.LightingColorFilter
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar


class ProgressBarAnimation(
    private val progressBar: ProgressBar,
    var from: Float,
    private val to: Float
) :
    Animation() {
    override fun applyTransformation(
        interpolatedTime: Float,
        t: Transformation?
    ) {
        super.applyTransformation(interpolatedTime, t)
        val value = from + (to - from) * interpolatedTime
        progressBar.progress = value.toInt()
        progressBar.progressDrawable.colorFilter =
            LightingColorFilter(Color.rgb(255 * progressBar.progress/ progressBar.max,
            255 - 255* progressBar.progress/ progressBar.max, 0), Color.BLACK)
    }

}