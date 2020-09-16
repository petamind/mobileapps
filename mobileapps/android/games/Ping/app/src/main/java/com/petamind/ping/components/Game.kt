package com.petamind.ping.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.MotionEvent
import com.petamind.ping.R

/**
 * @author Tung Nguyen
 */
class Game(context: Context, vsAI: Boolean = true, bounds: Rect) : GameLoop {

    enum class STATE {
        END, PAUSED, STARTED
    }

    var state = STATE.PAUSED
    var bounds: Rect
    var ball: Ball
    var players: Array<Player>

    init {
        this.bounds = bounds
        ball = Ball(context, R.drawable.ball)
        players = arrayOf(
            Player(context, R.drawable.button),
            if (vsAI) BotPlayer(context, R.drawable.button, this) else
                Player(context, R.drawable.button)
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

    override var updateRate: Int = 60
    override var timeToUpdate: Long = 0L

    override fun render(canvas: Canvas?) {
        ball.render(canvas)
        for (p in players) p.render(canvas)
    }

    override fun update() {
        ball.update()
        for (p in players) p.update()
        state = if (collide(bounds)) STATE.END else state
        for (p in players) collide(p)
    }

    fun processInput(o: Any?) {
        if (o is MotionEvent) {
            if (o.y > bounds.exactCenterY()) {
                players[0].location.offsetTo(o.x, players[0].location.top)
            } else if (players[1] !is BotPlayer) {
                players[1].location.offsetTo(o.x, 0f)
            }
        }
    }

    /**
     * Ball to any other object
     * @param o or Player
     * @return true = end game
     */
    fun collide(o: Any?): Boolean {
        if (o is Rect) {
            if (ball.location.left <= 0 || ball.location.right >= o.right) {
                ball.movVec.x = -ball.movVec.x
                if (ball.location.left <= 0) {
                    ball.location.offset(-ball.location.left, 0f)
                } else {
                    ball.location.offset(o.right - ball.location.right, 0f)
                }
            }

            if (ball.location.top <= 0 || ball.location.bottom >= o.bottom) {
                if (ball.location.top <= 0) {
                    players[0].score++
                } else {
                    players[1].score++
                }
                return true
            }
        } else if (o is Player) {
            if (o.location.contains(ball.location.centerX(), ball.location.bottom)) {
                ball.movVec.y = -ball.movVec.y
                ball.location.offset(0f, o.location.top - ball.location.bottom)
            } else if (o.location.contains(ball.location.centerX(), ball.location.top)
            ) {
                ball.movVec.y = -ball.movVec.y
                ball.location.offset(0f, o.location.bottom - ball.location.top)
            }
        }
        return false
    }
}