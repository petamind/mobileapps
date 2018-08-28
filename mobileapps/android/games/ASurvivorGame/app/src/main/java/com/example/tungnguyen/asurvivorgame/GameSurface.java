package com.example.tungnguyen.asurvivorgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback, GameLoop {
    private Logger logger = Logger.getLogger("GameSurface");
    private Random random_generator = new Random();
    private ArrayList<Integer> decorCoordinateX = new ArrayList<Integer>();
    private ArrayList<Integer> decorCoordinateY = new ArrayList<Integer>();
    private ArrayList<Bitmap> decorBitmaps = new ArrayList<Bitmap>();
    private Bitmap background_vertical1, background_vertical2, background_horizontal1, background_horizontal2;
    /////////////////////////////////////////////////////////////////////////////////////////////
    //Character
    private MainCharacter mainCharacter;
    private BoundingBox boundingBoxForMainCharacter;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 2: Declare global variables and objects
    //GameThread object
    private GameThread gameThread;//Declare GameThread object
    //Paint
    private Paint paint = new Paint();
    //gameOver variable
    private Boolean gameOver = false;
    //Canvas size
    private int screenWidth = 1794;//Default: landscape mode

    private int screenHeight = 1080;//Default: landscape mode
    //Variables for detect and handle TouchEvent
    private float startX, startY;
    private float endX, endY;
    private static final int MAX_PRESS_DURATION = 1000;//Max allowed duration to move during a "click"
    private static final int MAX_MOVEMENT_DISTANCE = 15; //Max allowed distance to move during a "click"
    private long pressStartTime;
    //Background Image
    private Bitmap background = null;
    //Background music
    private SoundPool soundPool;
    private boolean soundPoolLoaded;
    private static final int MAX_STREAMS = 50;
    private int backgroundSoundID, backgroundStreamID;

    //---------------------------------------------------------------------------
    //Later: Declare all your game objects, characters, monster, sprites, music, animation, etc. here
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 3: Initialize all your game objects
    public void initializeGameObjects() {
        //Initialize all characters, monster, sprites, music, animation, etc. here
        boundingBoxForMainCharacter = new BoundingBox(100, 100, screenWidth - 100, screenHeight-100);
        //Decode and load the main character bitmap
        Bitmap mainCharacterBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.maincharacter_bitmap); //Create the first appeared coordinates (x,y) of main character at the screen centre
        int x = screenWidth / 2;
        int y = screenHeight / 2;
        mainCharacter = new MainCharacter(this, boundingBoxForMainCharacter, mainCharacterBitmap, x, y);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 4a: Perform movement and update states when game is still Active.
    public void performMovementAndUpdateWhenGameIsActive() {
        //Later: Perform all update and movements for your objects, characters, etc. here
        mainCharacter.walk();
    }

    //Step 4b: Perform movement and update states when game is Over.
    public void performMovementAndUpdateWhenGameIsOver() {
        //Later: Perform all update and movements for your objects, characters, etc. here
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 5a: Perform rendering or redraw new game scene when game is still Active
    public void performRenderingWhenGameIsActive(Canvas canvas, Paint paint) {
        //Later: Perform all rendering or re-drawing your objects, characters, monster, etc. here
        //Render 10 background decorations on canvas
        //logger.log(Level.INFO, "Rendering when game is active" + decorBitmaps.size());
        for (int i = 0; i < decorBitmaps.size(); i++) {
            canvas.drawBitmap(decorBitmaps.get(i), decorCoordinateX.get(i), decorCoordinateY.get(i), paint);
        }
        //Render vertical and horizontal images -Upper horizontal image
        Rect dest = new Rect(0, 0, screenWidth, 100);
        canvas.drawBitmap(background_horizontal1, null, dest, paint);
        //Lower horizontal image
        dest = new Rect(0, getHeight() - 100, screenWidth, getHeight());
        canvas.drawBitmap(background_horizontal2, null, dest, paint);
        //Left vertical image
        dest = new Rect(0, 0, 100, getHeight());
        canvas.drawBitmap(background_vertical1, null, dest, paint);
        //Right vertical image
        dest = new Rect(getWidth() - 100, 0, getWidth(), getHeight());
        canvas.drawBitmap(background_vertical2, null, dest, paint);
        ///
        mainCharacter.draw(canvas);

    }

    //Step 5b: Perform movement and update states when game is Over.
    public void performRenderingWhenGameIsOver(Canvas canvas, Paint paint) {
        //Later: Perform all rendering or re-drawing your objects, characters, monster, etc. here
        //Render 10 background decorations on canvas
        //logger.log(Level.INFO, "Rendering when game is over");
        for (int i = 0; i < decorBitmaps.size(); i++) {
            canvas.drawBitmap(decorBitmaps.get(i), decorCoordinateX.get(i), decorCoordinateY.get(i), paint);
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 6: Read input or detect and handle user inputs by overriding onTouchEvent() method
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Get the type of user gesture: click or move (swipe/fling)
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                //Get the position where finger touch down the screen
                startX = event.getX();
                startY = event.getY();
                pressStartTime = System.currentTimeMillis();//Record moment of action down
                break;
            case (MotionEvent.ACTION_UP):
                //Get the position where finger releases the screen
                endX = event.getX();
                endY = event.getY();
                //Calculate the finger movement distance & duration
                float deltaX = endX - startX;
                float deltaY = endY - startY;
                double movementDistance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                long movementDuration = System.currentTimeMillis() - pressStartTime;
                float scalingFactor = 5.0f / ((screenWidth > screenHeight) ? screenWidth : screenHeight);
                //Check the type of gesture
                if ((movementDuration < MAX_PRESS_DURATION) && (movementDistance < MAX_MOVEMENT_DISTANCE)) {
                    //User input is "Single click gesture"
//------------------------------------------------------------------------------
                    //------------------------------------------------------------------------------
                } else if ((movementDuration > MAX_PRESS_DURATION) && (movementDistance < MAX_MOVEMENT_DISTANCE)) {
                    //User input is "Long click"
//------------------------------------------------------------------------------
                    //------------------------------------------------------------------------------
                } else if (movementDistance > MAX_MOVEMENT_DISTANCE) {
                    //User input is "Swipes/flings"
//------------------------------------------------------------------------------
                    //------------------------------------------------------------------------------
                }
                break;

            default:
                //Errors or Exceptions
                break;
        }
        //Return value
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 7: Constructor
    public GameSurface(Context context) {
        super(context);
        this.setFocusable(true); //Set the Surface focusable so that it can listen and then handle the event
        this.getHolder().addCallback(this);//Set all the events (touch, click, etc) related to GamePlay
        /////////////////////////////
        //Initialize soundPool object
        //For Android API >= 21
        if (Build.VERSION.SDK_INT >= 21) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);
            soundPool = builder.build();
        }
        //For Android API < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }
        ///////////////////////////////////////////////
        //Load and play background sound with soundPool
        backgroundSoundID = soundPool.load(this.getContext(), R.raw.background, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPoolLoaded = true;
                //Play background sound with soundPool when the soundPool event has been uploaded successfully to memory
                if (soundPoolLoaded) {
                    backgroundStreamID = soundPool.play(backgroundSoundID, 0.8f, 0.8f, 1, -1, 1f);
                }
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 8: Create surfaceCreated() method which is called as soon as the surface gets created.
    public void surfaceCreated(SurfaceHolder holder) {
        //Get the real screen Width and Height of the device
        screenWidth = getWidth();
        screenHeight = getHeight();
        //Decode and load background bitmap stored in "drawable" folder to the game scene
        background = BitmapFactory.decodeResource(this.getResources(), R.drawable.background);

        for (int i = 0; i < 10; i++) {
            int x = random_generator.nextInt(getWidth() - 300) + 200;
            decorCoordinateX.add(x);
            int y = random_generator.nextInt(getHeight() - 300) + 200;
            decorCoordinateY.add(y);
            int bitmapID = getResources().getIdentifier("background_decor" + i, "drawable", getContext().getPackageName());
            Bitmap deco = BitmapFactory.decodeResource(getResources(), bitmapID);

            decorBitmaps.add(deco);
        }

        background_horizontal1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.background_horizon1);
        background_horizontal2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.background_horizon2);
        background_vertical1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.background_vertical1);
        background_vertical2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.background_vertical2);

        //Initialize all objects and variables for your game here
        initializeGameObjects();
        //Assign states to Game thread and start to run Game thread
        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 9: Override draw() method of the SurfaceView class: Called back to draw the SurfaceView
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //Set properties for "paint" object
        paint.setStyle(Paint.Style.FILL);
        //Make the entire canvas WHITE
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
        //Draw the background image
        Rect src = new Rect(0, 0, background.getWidth(), background.getHeight());
        Rect dest = new Rect(0, 0, screenWidth, screenHeight);
        canvas.drawBitmap(background, src, dest, paint);//stretch background image to entire screen
        //Check for Game State changes: "over" or "not over".
        if (!gameOver) {
            //Perform rendering and redraw new game scene when the game is not yet over
            performRenderingWhenGameIsActive(canvas, paint);
        } else {
            //Perform rendering and redraw new game scene when the game is over
            performRenderingWhenGameIsOver(canvas, paint);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 10: create update() method to perform update and movement
    @Override
    public void update() {
        if (!gameOver) {
            //Perform update and movement when game is not yet over
            performMovementAndUpdateWhenGameIsActive();

        } else {
            //Perform update and movement when game is over
            performMovementAndUpdateWhenGameIsOver();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 11: Get canvas size by calling surfaceChanged() method immediately after any structural changes (format or size)
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //Update the screenWidth and screenHeight when they're changed
        screenWidth = width;
        screenHeight = height;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 12: create surfaceDestroy() method: be called as soon as the surface is destroyed(hidden) from the screen.
    //when the game surface is destroyed, we also destroy the “game thread” by calling its join() function.
    public void surfaceDestroyed(SurfaceHolder holder) {
        //while the Game surface is still alive, do the loop.
        while (gameThread.isAlive()) {
            try {
                this.gameThread.setRunning(false);
                //The parent thread needs to pause and wait the GameThread finishes its tasks
                this.gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Stop playing background audio when the game is closed
        if (!gameThread.isAlive()) {
            //Stop playing background music and release soundPool
            soundPool.stop(backgroundStreamID);
            soundPool.release();
        }
    }
}
