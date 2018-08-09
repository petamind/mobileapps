package com.example.tungnguyen.gamethread;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {
    private final int FPS = 30;
    private final int delay = 1000/30;//frame rate
    private final SurfaceHolder surfaceHolder;
    private GameView gamePanel;
    private boolean running;

    public GameThread(SurfaceHolder surfaceHolder, GameView gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    @Override
    public void run() {
        while (running) {

            Canvas canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                    Logger.getAnonymousLogger().log(Level.INFO, "running");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        this.sleep(delay);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }



        }

    }

}
