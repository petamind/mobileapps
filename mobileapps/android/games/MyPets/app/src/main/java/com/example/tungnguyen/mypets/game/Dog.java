package com.example.tungnguyen.mypets.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.view.MotionEvent;
import android.view.View;

import com.example.tungnguyen.mypets.R;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Dog extends Animal {


    public Dog(Context context) {
        super(context);
        location.set(100, 100);
        shape = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.dog);
        this.bound.set(location.x, location.y, location.x+shape.getWidth(), location.y + shape.getHeight());
    }



    @Override
    public String makeNoise() {
        return null;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(shape, location.x - bound.width()/2, location.y -bound.height()/2, paint);
        Logger.getLogger(this.toString()).log(Level.INFO, "Drawing");
    }

    @Override
    public void move(Point newLocation) {
        this.bound.set(location.x -bound.width()/2, location.y - bound.height()/2, location.x+shape.getWidth()/2, location.y + shape.getHeight()/2);
        super.move(newLocation);

    }

}
