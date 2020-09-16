package com.petamind.ping.components

import android.content.Context

/**
 * @author Tung Nguyen
 *
 * This class is to create an Bot player that plays autonomously with human player.
 */
class BotPlayer(context: Context, shapeId: Int?, game: Game?) :
    Player(context, shapeId) {
    var game: Game?

    enum class TYPE {
        DUMP, NORMAL, SMART
    }

    var type = TYPE.NORMAL

    init {
        when (type) {
            TYPE.DUMP -> {
                movVec.x = 3f
                updateRate = 50
                location.right = (game!!.bounds.width() / 2.5).toFloat()
            }
            TYPE.NORMAL -> {
                movVec.x = 6f
                updateRate = 80
                location.right = (game!!.bounds.width() / 4).toFloat()
            }
            TYPE.SMART -> {
                movVec.x = 8f
                updateRate = 100
            }
        }
        this.game = game
    }

    override fun update() {
        if (shouldUpdate) {
            super.update()
            if (game!!.ball.movVec.y > 0 && location.centerX().toInt() == game!!.bounds.centerX()) {
                return
            }
            location.offset(movVec.x, movVec.y)
            if (game?.ball?.location!!.centerX() > location.right ||
                game?.ball?.location!!.centerX() < location.left && game!!.ball.movVec.y < 0
            ) {
                if ((location.centerX() - game?.ball?.location!!.centerX()) * movVec.x > 0) {
                    movVec.x = -movVec.x
                }
            } else if (location.left <= 0 || location.right >= game!!.bounds.width()) {
                movVec.x = -movVec.x
            }
        }
    }
}
