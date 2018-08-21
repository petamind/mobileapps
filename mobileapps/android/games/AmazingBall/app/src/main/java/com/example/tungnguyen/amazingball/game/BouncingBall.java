package com.example.tungnguyen.amazingball.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

public class BouncingBall extends GameObject{
    private float radius;
    private Paint paint;


    public BouncingBall(Point center, float radius, int color) {
        this.radius = radius;
        setColor(color);
        setLocation(center);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        Random random = new Random();
        movementVector = new Point(random.nextInt(10)+2, random.nextInt(10)+2);
        bound = new Rect((int)(center.x -radius), (int)(center.y - radius),
                (int)(center.x + radius), (int)(center.y +radius));
    }

    @Override
    public void move() {
        location.x += movementVector.x;
        location.y += movementVector.y;
        bound.set((int)(location.x - radius + movementVector.x), (int)(location.y - radius + movementVector.y),
                (int)(location.x + radius + movementVector.x), (int)(location.y + radius + movementVector.y));
        if(location.x <= radius || location.x >= Constants.WIDTH - radius){
            movementVector.x = -movementVector.x;
        }
        if(location.y <= radius ){
            movementVector.y = - movementVector.y;
        }

        if(location.y >= Constants.HEIGHT - radius){
            movementVector.set(0,0);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(shape!= null){
            canvas.drawBitmap(shape, location.x, location.y, null);
        }
        else {
            paint.setColor(Color.RED);
            canvas.drawCircle(location.x, location.y, radius, this.paint);
        }
    }

    @Override
    public boolean collide(GameObject otherGameObject) {
        return bound.intersect(otherGameObject.getBound());
    }
}
