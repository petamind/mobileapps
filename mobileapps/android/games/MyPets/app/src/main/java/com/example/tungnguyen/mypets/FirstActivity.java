package com.example.tungnguyen.mypets;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    private SoundPool soundPool;
    private int clickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first);

        findViewById(R.id.button).setOnClickListener(this);
        AudioAttributes.Builder attributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).setUsage(AudioAttributes.USAGE_GAME);
        SoundPool.Builder builder = new SoundPool.Builder().setMaxStreams(2).setAudioAttributes(attributes.build());
        soundPool = builder.build();
        clickSound = soundPool.load(this, R.raw.button_click, 1);
    }

    @Override
    public void onClick(View view) {
        soundPool.play(clickSound, 1f, 1f, 1, 0, 1);
        startActivity(new Intent(this, GameActivity.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
    }
}
