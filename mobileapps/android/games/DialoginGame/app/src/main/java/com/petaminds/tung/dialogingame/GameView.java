package com.petaminds.tung.dialogingame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;


public class GameView extends View{

    private Handler handler;
    private int score = 20;//fake data

    public GameView(Context context, Handler handler) {
        super(context);
        this.handler = handler;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.RED);
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                //fake event
                handler.sendEmptyMessage(0);// when game finish tell user to input
                break;
        }
        return true;
    }
}
