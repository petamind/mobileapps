package com.petaminds.tung.collisiondetection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @author Tung Nguyen
 * @version 1.0
 */
public class GameView extends View {
    private GestureDetector detector;
    private ArrayList<MyObject> myObjects = new ArrayList<>();
    private Paint paint = new Paint();
    private MyObject selecteObj;

    public GameView(Context context) {
        super(context);
        init();
    }

    /**
     * This is a constructor. It has 2 params
     * @param context Application context
     * @param attrs is some kind of properties...
     *
     */
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        detector = new GestureDetector(getContext(), new MyGestureDetector());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //TODO: handle selecting an obj
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                checkObjSelection((int)event.getX(), (int)event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                moveObj((int)event.getX(), (int)event.getY());
                break;
            case MotionEvent.ACTION_UP:
                unselectObj();
                break;
        }

        //pass the touch event to gesture detector
        detector.onTouchEvent(event);

        return true;
    }

    private void unselectObj() {
        selecteObj = null;
    }

    private void moveObj(int x, int y) {
        //1. check if an obj is selected
        //2. move the selected obj to the new location.
        if(selecteObj != null){
            selecteObj.move(new Point(x, y));
            //check if it touches another obj
            for (MyObject obj : myObjects) {
                if(obj != selecteObj &&
                        new Rect(obj.getShape())
                                .intersect(selecteObj.getShape())){
                    selecteObj.setColor(Color.RED);
                }
            }


            invalidate();
        }
    }

    private void checkObjSelection(int x, int y){
        for (MyObject obj: myObjects) {
            //if the point lies inside an obj, then select it and esp.
            if(obj.getShape().contains(x, y)){
                selecteObj = obj;
                break;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (MyObject obj :myObjects) {
            obj.draw(canvas, paint);
        }
    }

    /**
     * This is a private inner class, accessible by any methods of the outer class.
     * This is just the blueprint (design) of the dectector object
     */
    private class MyGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Toast.makeText(getContext(), "Added a new obj!", Toast.LENGTH_SHORT).show();
            // create a new obj with constructor
            MyObject newObject =new MyObject(new Rect(100, 100, 200, 200)
                    , Color.CYAN, new Point(getWidth()/2, getHeight()/2));
            //add newobj to the list
            myObjects.add(newObject);
            //schedule redraw
            invalidate();
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Toast.makeText(getContext(), "single tapped me???", Toast.LENGTH_SHORT).show();
            return super.onSingleTapConfirmed(e);
        }
    }

}
