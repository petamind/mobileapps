package com.example.tungnguyen.asurvivorgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    private volatile boolean running;
    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 2: Constructor
    public GameThread(GameSurface gameSurface, SurfaceHolder surfaceHolder) {
        this.gameSurface = gameSurface;
        this.surfaceHolder = surfaceHolder;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 3: Check if the thread is running, then call our own draw method on the canvas obtained from the surfaceHolder
    @Override
    public void run() {
        super.run();
        long startTime = System.nanoTime();
        //When the game thread is running, create canvas object, lock it to synchronize draw and update process
        while (running) {
            Canvas canvas = null;
            try {
                //Create the canvas object and lock it to synchronize draw and update processes
                canvas = this.surfaceHolder.lockCanvas();
                //Synchronize the update and draw processes
                synchronized (canvas) {
                    this.gameSurface.update();
                    this.gameSurface.draw(canvas);
                }
            } catch (Exception e) {
                //If error, do nothing
            } finally {
                if (canvas != null) {
                    //Unlock the canvas when synchronous process finishes
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            //Read the current time and calculate the moment to update Game Scene (GameSurface)
            long now = System.nanoTime();
            long waitTime = (now - startTime) / 1000000; //Convert time unit from ns to ms.
            if (waitTime < 100) {
                waitTime = 100; // Set waitTime = 100 milli-second.
            }
            //Delay 100 milisecond before update game scene (GameSurface)
            try {
                //Lock the game for “waiTime” duration before updating game scene
                this.sleep(waitTime);
            } catch (InterruptedException e) {

            }
            startTime = System.nanoTime();

        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 4: set the "running" state to the game thread
    public void setRunning(boolean running) {
        this.running = running;
    }

}
