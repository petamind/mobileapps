package com.example.tungnguyen.asurvivorgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 1: Declare global variables and objects
    private GameSurface gameSurface; //Declare an object of GameSurface
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Step 2: Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Step 3: Initialize the object
        gameSurface = new GameSurface(this);
        //Step 4: Call the setContentView() method
        setContentView(gameSurface);
        //Step 5: Request focus onvn View
        gameSurface.requestFocus();
    }
}
