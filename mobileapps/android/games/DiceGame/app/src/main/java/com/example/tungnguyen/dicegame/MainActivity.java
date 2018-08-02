package com.example.tungnguyen.dicegame;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageView blackDicel;

    private Button startBtn;
    private Button stopBtn;

    private AnimationDrawable diceAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blackDicel = findViewById(R.id.imageView);
        startBtn = findViewById(R.id.button);
        stopBtn = findViewById(R.id.button2);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            if(view == startBtn) {
                blackDicel.setImageResource(0);
                blackDicel.setBackgroundResource(R.drawable.roll_black_dice);
                diceAnimation = (AnimationDrawable) blackDicel.getBackground();
                diceAnimation.selectDrawable(new Random().nextInt(7));
                diceAnimation.start();
            }
            else {
                 diceAnimation.stop();
               blackDicel.setImageResource(getResources().getIdentifier("blackdice_face"+
                       (new Random().nextInt(6)+1),"drawable", this.getPackageName()));


            }

    }}
