package com.petaminds.tung.collisiondetection;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class MyObject {
    private Rect shape;
    private int color;
    private Point location;

    public MyObject(Rect shape, int color, Point location) {
        this.shape = shape;
        this.color = color;
        this.location = location;
        move(location);//move the shape to the location
    }

    /**
     * Move the current obj to the new location
     * @param newLocation input param
     */
    public void move(Point newLocation){
        this.location = newLocation;
        shape.set(location.x - shape.width()/2,
                location.y -shape.height()/2,
                location.x + shape.width()/2,
                location.y + shape.height()/2);
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(color);
        canvas.drawRect(shape, paint);
    }

    public Rect getShape() {
        return shape;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
