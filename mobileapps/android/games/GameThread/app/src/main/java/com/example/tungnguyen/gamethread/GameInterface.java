package com.example.tungnguyen.gamethread;

import android.graphics.Canvas;

public interface GameInterface {
    void draw(Canvas c);
    void start();
    void stop();
    void pause();

}
