package com.example.tungnguyen.gamethread;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface GameInterface {
    static boolean running = false;
    void draw(Canvas c, Paint paint);
    void start();
    void stop();
    void pause();
}
