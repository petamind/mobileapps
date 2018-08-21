package com.example.tungnguyen.amazingball.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private BouncingBall bouncingBall;
    private Plate plate;
    private GameThread thread;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    private void init(){
        Constants.WIDTH = getWidth();
        Constants.HEIGHT = getHeight();
        bouncingBall = new BouncingBall( new Point(Constants.WIDTH/2,
                Constants.HEIGHT/2), 40, Color.RED);

        plate = new Plate(new Rect(100, 100, 300, 150), Color.BLUE
                , Constants.WIDTH, Constants.HEIGHT);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        if (bouncingBall.collide(plate)) {
            if (bouncingBall.getLocation().y >= plate.bound.top) {
                bouncingBall.getMovementVector().y = -bouncingBall.getMovementVector().y;
            }
//            else {
//                bouncingBall.getMovementVector().x = -bouncingBall.getMovementVector().x;
//            }
        }
        bouncingBall.move();
        bouncingBall.draw(canvas);
        plate.draw(canvas);


    }

    /**
     * Handle player input
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        plate.setLocation(new Point((int)event.getX(), (int)event.getY()));
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        init();
        if(thread == null){
            thread = new GameThread(this, getHolder());
            thread.setRunning(true);
            thread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if(thread != null && thread.isAlive()){
            thread.setRunning(false);
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
