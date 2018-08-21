package com.example.tungnguyen.amazingball.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private GameView gameView;
    private SurfaceHolder holder;
    private volatile boolean running;

    public GameThread(GameView gameView, SurfaceHolder holder) {
        this.gameView = gameView;
        this.holder = holder;
    }

    @Override
    public void run() {
        Canvas canvas;
        while (running) {
            if(holder.getSurface().isValid()){
                canvas = holder.lockCanvas();

                //canvas.drawColor(Color.WHITE);
                gameView.draw(canvas);

                holder.unlockCanvasAndPost(canvas);
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
