package com.example.tungnguyen.asurvivorgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MainCharacter extends Sprite {
    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 1: Declare properties/data of the class
//The movement directions
    private static final int ROW_TOP_TO_BOTTOM = 0;
    private static final int ROW_RIGHT_TO_LEFT = 1;
    private static final int ROW_LEFT_TO_RIGHT = 2;
    private static final int ROW_BOTTOM_TO_TOP = 3;
    //The current image row is using
    private int rowUsing = ROW_LEFT_TO_RIGHT;
    private int colUsing;
    private Bitmap[] leftToRights;
    private Bitmap[] rightToLefts;
    private Bitmap[] topToBottoms;
    private Bitmap[] bottomToTops;
    //The movement speed/velocity of the character = pixels/millisecond
    public static final float VELOCITY = 0.1f;
    private float movingVectorX = 10;
    private float movingVectorY = 5;
    private long lastDrawNanoTime = -1;
    //Game Surface
    private GameSurface gameSurface;
    //Bounding box
    private BoundingBox boundingBox;

    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 2: Constructor
    public MainCharacter(GameSurface gameSurface, BoundingBox boundingBox, Bitmap image, int x, int y) {
        super(image, 4, 3, x, y); //DEFINE ROW = 4 and COL = 3 IN THE SUPERCLASS: SPRITE CLASS //
        this.gameSurface = gameSurface;
        this.boundingBox = boundingBox;
//
        this.topToBottoms = new Bitmap[colCount];//colCount = 3
        this.rightToLefts = new Bitmap[colCount];//colCount = 3
        this.leftToRights = new Bitmap[colCount];//colCount = 3
        this.bottomToTops = new Bitmap[colCount];//colCount = 3 //Extract sub images from the entire image
        for (int col = 0; col < colCount; col++) {
            this.topToBottoms[col] = createSubImageAt(ROW_TOP_TO_BOTTOM, col);
            this.rightToLefts[col] = createSubImageAt(ROW_RIGHT_TO_LEFT, col);
            this.leftToRights[col] = createSubImageAt(ROW_LEFT_TO_RIGHT, col);
            this.bottomToTops[col] = createSubImageAt(ROW_BOTTOM_TO_TOP, col);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 3: draw() method - draw the current Character image on "canvas" surface
    public void draw(Canvas canvas) {
//Get the current array of sub-images according to movement direction
        Bitmap[] currentBitmapArray;
        switch (rowUsing) {
            case ROW_BOTTOM_TO_TOP:
                currentBitmapArray = bottomToTops;
                break;
            case ROW_LEFT_TO_RIGHT:
                currentBitmapArray = leftToRights;
                break;
            case ROW_RIGHT_TO_LEFT:
                currentBitmapArray = rightToLefts;
                break;
            case ROW_TOP_TO_BOTTOM:
                currentBitmapArray = topToBottoms;
                break;
            default:
                //Error or Exception
                currentBitmapArray = null;
                break;
        }
            //Determine the current Character image needed to be drawn on Canvas
        Bitmap currentMoveBitmap = currentBitmapArray[colUsing];
        canvas.drawBitmap(currentMoveBitmap, x, y, null); //Draw the current character image on canvas
            // Take note the last moment of drawing the character image to canvas (Nanosecond).
        lastDrawNanoTime = System.nanoTime();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /// //Step 4: walk() method - calculate & update the next sub-image
    public void walk() {
        //Calculate and update colUsing
        colUsing++;
        if (colUsing >= colCount) {
            colUsing = 0;
        }
        //Take note the moment of updating in nano-second
        long now = System.nanoTime();
        //If no images have been never drawn (when the game is launched), the last moment of drawing = current time.

        if (lastDrawNanoTime == -1) {
            lastDrawNanoTime = now;
        }
        int deltaTime = (int) ((now - lastDrawNanoTime) / 1000000);//Convert ns into ms (1 ms = 1000000 ns).
        //Calculate the distance that character has gone through (in pixels).
        float distance = VELOCITY * deltaTime;
        double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX + movingVectorY * movingVectorY);
        //Calculate & update the new position of character
        x = x + (int) (distance * movingVectorX / movingVectorLength);
        y = y + (int) (distance * movingVectorY / movingVectorLength);

        //Detect whether the character reaches the vertical screen border. If yes, bounce back
        if (x < boundingBox.getXmin()) {
        //Reach the left screen border ==> bounce back
            x = boundingBox.getXmin();
            movingVectorX = -movingVectorX;
        } else if (x > boundingBox.getXmax() - width) { //Reach the right screen border ==> bounce back
            x = boundingBox.getXmax() - width;
            movingVectorX = -movingVectorX;
        }
        //Detect whether the character reaches the horizontal screen border. If yes, bounce back
        if (y < boundingBox.getYmin()) {
        //Reach the upper screen border ==> bounce back
            y = boundingBox.getYmin();
            movingVectorY = -movingVectorY;
        } else if (y > boundingBox.getYmax() - height) { //Reach the lower screen border ==> bounce back
            y = boundingBox.getYmax() - height;
            movingVectorY = -movingVectorY;

        }

        if (movingVectorX > 0) {
        //Character moves from Left to Right
            if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                //Character moves downward
                rowUsing = ROW_TOP_TO_BOTTOM;
            } else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                //Character moves upward
                rowUsing = ROW_BOTTOM_TO_TOP;
            } else {
        //Character moves straightly from Left to Right
                rowUsing = ROW_LEFT_TO_RIGHT;
            }
        } else {
        //Character moves from Right to Left
            if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
        //Character moves from Upward
                rowUsing = ROW_TOP_TO_BOTTOM;
            } else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
        //Character moves from Downward
                rowUsing = ROW_BOTTOM_TO_TOP;
            } else {
        //Character moves straightly from Right to Left
                rowUsing = ROW_RIGHT_TO_LEFT;
            }
        }
    }

    //Step 5: setMovingVector() method - to set/change the character's movement velocity vector
    public void setMovingVector(int movingVectorX, int movingVectorY) {
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
    }

    //Step 6: getMovingVector() method - to get the current character's movement velocity vector
    public float[] getMovingVector() {
        float[] movingVector = {movingVectorX, movingVectorY};
        return movingVector;
    }

}