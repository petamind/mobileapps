package com.example.tungnguyen.amazingball.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Plate extends GameObject {

    private Rect rect;
    private Paint paint;

    public Plate(Rect rect, int color, int screenWidth, int screenHeight) {
        setColor(color);
        this.rect = rect;
        paint = new Paint();
        paint.setColor(color);
        bound = new Rect();
        setLocation(new Point(screenWidth/2, screenHeight - rect.height()/2));
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rect, this.paint);
        //canvas.drawRect(bound, this.paint);
    }

    @Override
    public boolean collide(GameObject otherGameObject) {
        return false;
    }

    @Override
    public void setLocation(Point location) {
        super.setLocation(location);
        rect.set(location.x - rect.width()/2, Constants.HEIGHT- rect.height()-100,
                location.x + rect.width()/2, Constants.HEIGHT -100);
        bound.set(rect);
        //Logger.getAnonymousLogger().log(Level.INFO, rect.toString());
    }
}
