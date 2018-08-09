package com.example.tungnguyen.gamethread2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private MyClockView myClockView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myClockView = new MyClockView(this);
        setContentView(myClockView);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        myClockView.setRunning(hasFocus);
        myClockView.clockStartStop();
    }

    private class MyClockView extends SurfaceView implements Runnable {

        private String time;
        private volatile boolean running= true;
        private Date date;
        private SimpleDateFormat hms = new SimpleDateFormat("HH:mm:ss");
        private final Paint paint = new Paint();
        private Thread clockThread;
        private SurfaceHolder mSurfaceHolder;

        public MyClockView(Context context) {
            super(context);
            init();
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        public MyClockView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public MyClockView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init(){
            date = new Date();
            time = hms.format(date);
            mSurfaceHolder = getHolder();
        }

        public void clockStartStop(){
            if(running && clockThread == null){
                clockThread = new Thread(this);
                clockThread.start();
            }  else{
                if(clockThread != null && clockThread.isAlive()){
                    try {
                        clockThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void run() {
            Canvas canvas;
            while (running){
                if (mSurfaceHolder.getSurface().isValid()) {

                    date = new Date();
                    time = hms.format(date);

                    canvas = mSurfaceHolder.lockCanvas();

                    canvas.drawColor(Color.WHITE);
                    paint.setStyle(Paint.Style.FILL_AND_STROKE);
                    paint.setColor(Color.RED);
                    paint.setTextSize(100f);
                    canvas.drawText(time, getWidth()/2, getHeight()/2, paint);

                    mSurfaceHolder.unlockCanvasAndPost(canvas);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }

    }
}
