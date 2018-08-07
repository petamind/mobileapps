package com.example.tungnguyen.gamethread;

public class GameThread extends Thread {
    private int speed = 30;//fps
    private int delay = 1000/speed;

    public GameThread(int speed) {
        this.speed = speed;
        setDelay(1000/speed);
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public GameThread(Runnable target, int speed) {
        super(target);
        this.speed = speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {
        super.run();
    }
}
