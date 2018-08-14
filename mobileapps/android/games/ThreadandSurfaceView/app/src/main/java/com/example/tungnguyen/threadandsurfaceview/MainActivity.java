package com.example.tungnguyen.threadandsurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private MyClock myClock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myClock = new MyClock(this);
        setContentView(myClock);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        myClock.running = hasFocus;
        if(hasFocus){
            new Thread(myClock).start();
        }
    }

    class MyClock extends SurfaceView implements Runnable {
        private volatile boolean running;
        private SurfaceHolder surfaceHolder;
        private int counter;
        private Paint paint;

        public MyClock(Context context) {
            super(context);
            init();
        }

        private void init(){
            surfaceHolder = getHolder();
            paint = new Paint();
            paint.setTextSize(50f);
            paint.setColor(Color.RED);
        }

        @Override
        public void run() {
            Canvas canvas;
            while (running){
                if(surfaceHolder.getSurface().isValid())
                {
                    canvas = surfaceHolder.lockCanvas();

                    counter++;
                    canvas.drawColor(Color.WHITE);
                    float textWidth = paint.measureText(counter+"");
                    canvas.drawText(counter + "", getWidth()/2-textWidth/2, getHeight()/2, paint);

                    surfaceHolder.unlockCanvasAndPost(canvas);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
