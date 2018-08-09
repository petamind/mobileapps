package com.example.tungnguyen.gamethread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gameView = new GameView(this);
        setContentView(gameView);
        //gameView = findViewById(R.id.gameView);

        gameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = !running;
                if(!running)
                {
                    gameView.start();

                } else {
                    gameView.stop();
                }
            }
        });

    }


}
