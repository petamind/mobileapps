package com.example.tungnguyen.bouncingballs;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;

public class Balloon {
    private Point location;

    private float radius = 80;
    private int color;
    private float speedX = 5;
    private float speedY = 3;

    public Balloon(Point location, float radius, int color, float speedX, float speedY) {
        this.location = location;
        this.radius = radius;
        this.color = color;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(color);
        canvas.drawCircle(location.x, location.y, radius, paint);
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void move(int screen_xMin, int screen_yMin, int screen_xMax, int screen_yMax) {
        //Set upcoming coordinate for balloon: Move the balloon according to its speed
        location.x += speedX;
        location.y += speedY;
        //Detect whether the balloon touches the vertical walls/edges. If yes, the balloon bounces back
        if (location.x + radius > screen_xMax) {
            //Touch the right wall ==> bounce back
            speedX = -speedX;
            location.x = (int)(screen_xMax - radius);
        } else if (location.x - radius < screen_xMin) {
            //Touch the left wall ==> bounce back
            speedX = -speedX;
            location.x = (int)(screen_xMin + radius);
        }
        //Detect whether the balloon touches the horizon walls/edges. If yes, the balloon bounces back
        if (location.y + radius > screen_yMax) {
            //Touch the bottom wall ==> bounces back
            speedY = -speedY;
            location.y = (int)(screen_yMax - radius);
        } else if (location.y - radius < screen_yMin) {
            //Touch the upper wall ==> bounce back
            speedY = -speedY;
            location.y = (int)(screen_yMin + radius);
        }

    }
}
