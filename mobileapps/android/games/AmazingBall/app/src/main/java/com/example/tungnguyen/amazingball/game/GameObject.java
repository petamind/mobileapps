package com.example.tungnguyen.amazingball.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public abstract class GameObject {

    protected Point location;
    protected int color;
    /**
     * indicate the movement speed and direction
     */
    protected volatile Point movementVector;
    protected Bitmap shape;
    protected Rect bound;

    public abstract void move();
    public abstract void draw(Canvas canvas);
    public abstract boolean collide(GameObject otherGameObject);

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Point getMovementVector() {
        return movementVector;
    }

    public void setMovementVector(Point movementVector) {
        this.movementVector = movementVector;
    }

    public Bitmap getShape() {
        return shape;
    }

    public void setShape(Bitmap shape) {
        this.shape = shape;
    }

    public Rect getBound() {
        return bound;
    }
}
