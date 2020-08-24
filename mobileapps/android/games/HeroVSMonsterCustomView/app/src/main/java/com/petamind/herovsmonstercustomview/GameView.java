package com.petamind.herovsmonstercustomview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;
import java.util.logging.LogRecord;

/**
 * The custom view implementation
 *
 */
public class GameView extends View {
    private Bitmap hero;
    private Rect heroBounds;
    private Bitmap monster;
    private Rect monsterBounds;

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {

            invalidate();
        }
    };

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        hero = BitmapFactory.decodeResource(getResources(), R.drawable.ic_hero);
        monster = BitmapFactory.decodeResource(getResources(), R.drawable.ic_monster);
        heroBounds = new Rect(0 , 0, hero.getWidth(), hero.getHeight());
        //Make monster appear in random place
        monsterBounds = new Rect(200 , 200,
                200 + monster.getWidth(), 200 + monster.getHeight());
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if(gainFocus) {
            randomMonster();
        }
    }

    public void randomMonster(){
        Random r = new Random();
        int x = r.nextInt(getWidth() - 2 *monster.getWidth());
        int y = r.nextInt(getHeight() - 2 * monster.getHeight());
        monsterBounds = new Rect(x , y,
                x + monster.getWidth(), y + monster.getHeight());
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(hero, null, heroBounds, null);
        if(monster != null)
            canvas.drawBitmap(monster, null, monsterBounds, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        heroBounds.offsetTo((int)event.getX() - heroBounds.width()/2,
                (int)event.getY() - heroBounds.height()/2);
        //Check collision
        Rect herotemps = new Rect(heroBounds);
        if(herotemps.intersect(monsterBounds)) {
            //monster = null;
            randomMonster();
        }

        handler.sendEmptyMessage(0);

        return true;
    }
}
