package com.petamind.ping.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.petamind.ping.R

//https://stackoverflow.com/questions/24561596/smoothing-out-android-game-loop
class GameView(context: Context?) : SurfaceView(context), SurfaceHolder.Callback, Runnable,
    GameLoop {
    override var frameRate: Int = 120
    override var timeToUpdate: Long = System.currentTimeMillis()

    private var mThread: Thread? = null
    private var mRunning: Boolean = false
    lateinit var mCanvas: Canvas

    var mHolder: SurfaceHolder?
    private var bounds: Rect = Rect()
        set(value) {
            field = value
            setup()
        }

    private lateinit var ball: Ball
    private lateinit var players: Array<Player>


    init {
        mHolder = holder
        if (mHolder != null) {
            mHolder?.addCallback(this)
        }
    }

    private fun setup() {
        ball = Ball(this.context, R.drawable.ball)
        players = arrayOf(
            Player(this.context, R.drawable.button),
            Player(this.context, R.drawable.button)
        )
        players[0].location.offsetTo(
            bounds.exactCenterX() - players[0].location.width() / 2,
            bounds.bottom - players[0].location.height()
        )
        ball.location.offsetTo(
            players[1].location.centerX() - ball.location.centerX(),
            players[1].location.bottom
        )
    }

    fun start() {
        mRunning = true
        mThread = Thread(this)
        timeToUpdate = System.currentTimeMillis()
        mThread?.start()
    }

    fun stop() {
        mRunning = false
        try {
            // Stop the thread == rejoin the main thread.
            mThread?.join()
        } catch (e: InterruptedException) {
        }
    }

    override fun surfaceCreated(p0: SurfaceHolder) {

    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        Log.d(this.toString(), "surface changed")
        bounds = Rect(0, 0, p2, p3)
        start()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        stop()
    }

    override fun run() {
        while (mRunning) {
            while (shouldUpdate) {
                update()
            }
            render()
        }
    }

    override fun update() {
        timeToUpdate = System.currentTimeMillis() + 1000L / frameRate
        ball.update()
        mRunning = !ball.collide(bounds)
        for (p in players) {
            p.update()
            ball.collide(p)
        }
    }


    override fun render(canvas: Canvas?) {
        if (mHolder!!.surface?.isValid == true) {
            mCanvas = mHolder!!.lockCanvas()
            mCanvas.drawColor(Color.WHITE)
            ball.render(mCanvas)
            for (p in players) p.render(mCanvas)
            mHolder!!.unlockCanvasAndPost(mCanvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event!!.y > bounds.exactCenterY()) {
            players[0].location.offsetTo(event.x, players[0].location.top)
        } else {
            players[1].location.offsetTo(event.x, 0f)
        }
        return true
    }
}