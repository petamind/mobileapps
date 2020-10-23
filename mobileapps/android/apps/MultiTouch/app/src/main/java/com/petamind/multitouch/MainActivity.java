package com.petamind.multitouch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * @author tung.nguyen
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameScene(this));
    }
}