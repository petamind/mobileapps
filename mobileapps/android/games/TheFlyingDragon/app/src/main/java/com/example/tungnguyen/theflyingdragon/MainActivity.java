package com.example.tungnguyen.theflyingdragon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.sun_rise);
            findViewById(R.id.imageView).startAnimation(animation);
            Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.clock_turn);
            findViewById(R.id.imageView3).startAnimation(animation1);
        }
    }
}
