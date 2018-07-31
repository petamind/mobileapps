package com.example.tungnguyen.mypets.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PetHouseView extends View {
    private Logger logger;
    private Animal selectedPet;
    private ArrayList<Animal> pets;
    private int background;
    private Paint paint;
    private GestureDetector myGestureDetector;
    public PetHouseView(Context context) {
        super(context);
        init();
    }

    public PetHouseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PetHouseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        background = Color.GREEN;
        pets = new ArrayList<>();
        paint = new Paint();
        logger = Logger.getLogger(this.toString());
        myGestureDetector = new GestureDetector(getContext(), new GestureListener());

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(background);
        for (Animal a: pets
             ) {
            a.draw(canvas, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        myGestureDetector.onTouchEvent(event);
        if(selectedPet != null){
            selectedPet.move(new Point((int)event.getX(), (int)event.getY()));
        }
        invalidate();
        return true;
    }

    private class GestureListener extends android.view.GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            pets.add(new Dog(getContext()));
            logger.log(Level.INFO, "Drawing");
            return super.onDoubleTap(e);
        }

        @Override
        public void onShowPress(MotionEvent e) {


            super.onShowPress(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            for (Animal a: pets
                    ) {
                if(a.getBound().contains((int)e.getX(), (int)e.getY())){
                    a.setSelected(true);
                    selectedPet = a;
                    logger.log(Level.INFO, "Selected");
                    break;
                }
            }

            return super.onSingleTapConfirmed(e);
        }
    }
}