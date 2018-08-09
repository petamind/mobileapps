package com.example.tungnguyen.gamethread;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Hero {

    private Rect rectangle;
    private int color;

    public Hero(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
    }


    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(new RectF(rectangle), 50f, 50f, paint);
        Logger.getAnonymousLogger().log(Level.INFO, rectangle.toString());
    }

    public Rect getRectangle() {
        return rectangle;
    }


    public void move() {
        rectangle.top += 1;
        rectangle.bottom += 1;

    }



}
