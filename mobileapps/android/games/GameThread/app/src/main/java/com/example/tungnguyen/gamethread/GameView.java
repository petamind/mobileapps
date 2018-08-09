package com.example.tungnguyen.gamethread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class GameView extends SurfaceView {

    private GameThread thread;
    private Hero player;
    public volatile boolean running = true;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context) {
        super(context);
        init();
    }

    public void init() {
        setFocusable(true);
        player = new Hero(new Rect(100, 100, 200, 200), Color.LTGRAY);
    }

    public void start() {
        init();
        running = true;

        thread = new GameThread(getHolder(), this);

        thread.setRunning(running);
        thread.start();
    }

    public void stop() {
        if (thread != null && thread.isAlive()) {
            running = false;
            thread.setRunning(running);
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        update();
    }



    public void update() {
        player.move();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        player.draw(canvas);
    }
}
