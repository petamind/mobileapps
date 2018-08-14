package com.example.tungnguyen.threadonview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyClock(this));
        Logger.getAnonymousLogger().log(Level.INFO, "Thread info: " + Thread.currentThread().getName());
    }

    private class MyClock extends View implements Runnable {

        volatile int count;
        volatile boolean running;
        final Paint paint = new Paint();
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                invalidate();
            }
        };

        public MyClock(Context context) {
            super(context);
        }

        public MyClock(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public MyClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            canvas.drawColor(Color.WHITE);
            paint.setTextSize(50);
            canvas.drawText(count + "", getWidth()/2, getHeight()/2, paint);
            Logger.getAnonymousLogger().log(Level.INFO, "Thread info: " + Thread.currentThread().getName());
        }

        @Override
        public void onWindowFocusChanged(boolean hasWindowFocus) {
            super.onWindowFocusChanged(hasWindowFocus);
            if(hasWindowFocus){
                running = true;
                new Thread(this).start();

            } else {
                running = false;
            }
        }

        @Override
        public void run() {
            while (true){
                count++;
                Logger.getAnonymousLogger().log(Level.INFO, "Thread info: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }
    }
}
