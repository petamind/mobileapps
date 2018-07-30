package com.petaminds.tung.numberguessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //need to handle click event for Play button
        Button playButton = findViewById(R.id.playBtn);
        //add event handler/listener to the button
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start the second activity
                // 1. create an intent (or object of the second activity)
                Intent second = new Intent(FirstActivity.this, SecondActivity.class);
                // 2. start activity
                startActivity(second);
            }
        });
    }
}
