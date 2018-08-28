package com.example.tungnguyen.asurvivorgame;

import android.graphics.Canvas;

/**
 * We declare this interface to use as a game loop
 *
 * Each class which implements this inteface should be able to perform update and then draw to a canvas.
 */
public interface GameLoop {
    void update();
    void draw(Canvas canvas);
}
