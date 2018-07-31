package com.example.tungnguyen.mypets.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public abstract class Animal {
    static Bitmap shape;
    private Rect bound;
    protected Point location;
    protected Context context;

    public Animal(Context context) {
        this.context = context;
        location = new Point();
        bound = new Rect();
    }

    public abstract String makeNoise();
    public abstract void draw(Canvas canvas, Paint paint);
    public abstract void move(Point newLocation);
}
