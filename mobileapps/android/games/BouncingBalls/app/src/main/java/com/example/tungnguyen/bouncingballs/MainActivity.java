package com.example.tungnguyen.bouncingballs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private CanvasSurface canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        canvasView = new CanvasSurface(this);
        //Step 4: Call the setContentView() method
        setContentView(canvasView);
        //Step 5: Request focus on View
        canvasView.requestFocus();
    }
}
