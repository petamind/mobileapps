package com.example.tungnguyen.bouncingballs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class CanvasSurface extends View {

    private int screenWidth = 1080; ;//Default: portrait mode
    private int screenHeight = 1794; ;//Default: portrait mode
    //Variables for detect and handle TouchEvent
    private float startX, startY;
    private float endX, endY;
    private static final int MAX_PRESS_DURATION = 1000;//Max allowed duration to move during a "click"
    private static final int MAX_MOVEMENT_DISTANCE = 15; //Max allowed distance to move during a "click"
    private long pressStartTime;
    private Balloon balloon;
    //Rand variable to generate random number
    Random random = new Random();


    public CanvasSurface(Context context) {
        super(context);
        this.setFocusableInTouchMode(true);
        //Step 2: Initialize/create object: a RED balloon with the following properties:
        //Center coordinates = (0,0), radius = 100, color = RED, speedX=5, speedY=3
        balloon = new Balloon(new Point(), 100, Color.RED, 5, 3);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        screenWidth = getWidth();
        screenHeight = getHeight();
        //Initialize the paint object
        Paint paint = new Paint();
        ////////////////////////////////////////////////////////////////////////////////////////////
        //Draw all objects on canvas surface
        //Draw the background image on canvas
        //Bitmap background = BitmapFactory.decodeResource(this.getResources(), R.drawable.);
//Rect src = new Rect(0, 0, background.getWidth(), background.getHeight());
//        Rect dest = new Rect(0, 0, screenWidth, screenHeight);
//        canvas.drawBitmap(background, src, dest, paint);//stretch background image to entire screen
        //Step 3: Draw the RED balloon created above in the constructor on canvas
        balloon.draw(canvas, paint);
        ////////////////////////////////////////////////////////////////////////////////////////////
        //Step 4: Move the balloon around and check whether it collides the screen edges/walls
        balloon.move(0, 0, screenWidth, screenHeight);
        ////////////////////////////////////////////////////////////////////////////////////////////
        //Rendering all objects during 10 milli-second before forcing the canvas surface to be redrawn
        try {
            Thread.sleep(1000/50); //Pause the current thread to suspend execution for a specified period
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        //(4)Request the "canvas" surface be invalidated/redrawn: call again the onDraw() method
        invalidate(); // Force a re-draw

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                //Get the position where finger touch down the screen
                startX = event.getX();
                startY = event.getY();
                pressStartTime = System.currentTimeMillis();//Record moment of action down
                break;
            case (MotionEvent.ACTION_UP):
                //Get the position where finger releases the screen
                endX = event.getX();
                endY = event.getY();
                //Calculate the finger movement distance & duration
                float deltaX = endX - startX;
                float deltaY = endY - startY;
                double movementDistance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                long movementDuration = System.currentTimeMillis() - pressStartTime;
                float scalingFactor = 5.0f / ((screenWidth > screenHeight) ? screenWidth : screenHeight);
                //Check the type of gesture
                if ((movementDuration < MAX_PRESS_DURATION) && (movementDistance < MAX_MOVEMENT_DISTANCE)) {
                    //Single click gesture ==> Stop balloon moving around by setting the speedX=0 and speedY=0
                    balloon.setSpeedX(0);
                    balloon.setSpeedY(0);
                } else {
                    //Long click or Swipe gesture ==> Set new speed & direct to balloon according to the finger movement
                    balloon.setSpeedX(balloon.getSpeedX() + deltaX * scalingFactor);
                    balloon.setSpeedY(balloon.getSpeedY() + deltaY * scalingFactor);
                }
                break;
            default:
                //Errors or Exceptions
                break;
        }
        //Return value
        return true;
    }
}
