package com.petaminds.tung.dialogingame;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    // A handler to handle different event, e.g. game over
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            showDialog();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this, handler);
        setContentView(gameView);
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Test");
        dialog.setContentView(R.layout.dialog_layout);
        TextView tv = dialog.findViewById(R.id.message);
        tv.setText("Congrats! Your score is " + gameView.getScore());
        Button btn = dialog.findViewById(R.id.OK);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Do saving logic here
                Toast.makeText(MainActivity.this, "Data saved", Toast.LENGTH_LONG).show();//fake data
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
