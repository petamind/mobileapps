package com.petaminds.tung.numberguessinggame;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    //make the game global that I can access from any where in the class
    private NumberGuessingGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //manage the game
        game = new NumberGuessingGame();//object of the game
        //get the edittext
        final EditText editText = findViewById(R.id.numberET);
        //handle the check button
        Button checkButton = findViewById(R.id.checkBtn);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check result and give feedback
                //0.convert string input to integer
                int guessedNumber = Integer.parseInt(editText.getText().toString());
                //1. get feedback message from game obj
                String feeback = game.check(guessedNumber);
                //2. display the feedback on the screen
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this)
                        .setMessage(feeback).setIcon(android.R.drawable.stat_notify_chat);
                builder.create().show();
            }
        });
    }
}
