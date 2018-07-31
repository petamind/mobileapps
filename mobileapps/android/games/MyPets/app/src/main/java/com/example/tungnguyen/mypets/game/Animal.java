package com.example.tungnguyen.mypets.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public abstract class Animal {
    protected Point location;
    protected Context context;

    public Animal(Context context) {
        this.context = context;
        location = new Point();
    }

    public abstract String makeNoise();
    public abstract void draw(Canvas canvas, Paint paint);
}
