package com.example.tungnguyen.mypets.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
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
    }



    @Override
    public String makeNoise() {
        return null;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(shape, location.x, location.y, paint);
        Logger.getLogger(this.toString()).log(Level.INFO, "Drawing");
    }

    @Override
    public void move(Point newLocation) {
        this.location = newLocation;

    }

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        Logger.getLogger(this.toString()).log(Level.INFO, "Touching");
//        return false;
//    }
}
