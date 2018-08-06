package com.petaminds.tung.collisiondetection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fullscreen code go here.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //TODO: (1) Create a custom view and set it to the main_layout.xml (2 mins)
        //TODO: (2) Modify the custom view: bg = green (1 mins)
        //TODO: (2.1) Make it full screen (5mins)
        // a - set the no action bar theme for app_theme (manifest file)
        // b - add the code for window & set flags fullscreen (before setContentView)

        //TODO: (3) Handle double click event -> double click will add one object to the view
        //TODO: (4) Handle single click event -> click -> select an object
        //TODO: (5) Handle move gesture -> move -> move selected obj
        //TODO: (6) Handle collision with other obj
    }
}
