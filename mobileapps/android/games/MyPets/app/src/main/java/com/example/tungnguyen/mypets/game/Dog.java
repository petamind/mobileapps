package com.example.tungnguyen.mypets.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.tungnguyen.mypets.R;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Dog extends Animal {


    public Dog(Context context) {
        super(context);
        location.set(100, 100);
    }

    @Override
    public String makeNoise() {
        return null;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.dog);

        canvas.drawBitmap(icon, location.x, location.y, paint);
        Logger.getLogger(this.toString()).log(Level.INFO, "Drawing");
    }
}
