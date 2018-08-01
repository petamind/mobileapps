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
    protected boolean selected;
    protected Rect bound;
    protected Point location;
    protected Context context;

    public Animal(Context context) {
        this.context = context;
        location = new Point();
        bound = new Rect();
    }

    public Rect getBound() {
        return bound;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public abstract String makeNoise();
    public abstract void draw(Canvas canvas, Paint paint);
    public void move(Point newLocation){
        this.location = newLocation;
    };
}
