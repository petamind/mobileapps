package com.petamind.herovsmonstercustomview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * The simple implemetation of Custom View and touch events
 * It is NOT recommended to use this in a more complicated game.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}