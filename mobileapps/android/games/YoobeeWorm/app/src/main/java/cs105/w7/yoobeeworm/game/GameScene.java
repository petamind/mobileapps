package cs105.w7.yoobeeworm.game;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.Random;

public class GameScene extends SurfaceView implements Runnable, GameLoop {

    // Our game thread for the main game loop
    private Thread thread = null;

    // To hold a reference to the Activity
    private Context context;
    private int eat_bob = -1;
    private int snake_crash = -1;

    // Start by heading to the right
    private Heading heading = Heading.RIGHT;

    // To hold the screen size in pixels
    private int screenX;
    private int screenY;

    private Worm worm;
    // How long is the snake
    //private int snakeLength;

    // Where is Bob hiding?
    private int bobX;
    private int bobY;

    // The size in pixels of a snake segment
   // private int blockSize;

    // The size in segments of the playable area
    private final int NUM_BLOCKS_WIDE = 40;
    private int numBlocksHigh;

    // Control pausing between updates
    private long nextFrameTime;
    // Update the game 10 times per second
    private final long FPS = 10;
    // There are 1000 milliseconds in a second
    private final long MILLIS_PER_SECOND = 1000;
// We will draw the frame much more often

    // How many points does the player have
    private int score;

    // The location in the grid of all the segments
//    private int[] snakeXs;
//    private int[] snakeYs;

    // Everything we need for drawing
// Is the game currently playing?
    private volatile boolean isPlaying;

    // A canvas for our paint
    private Canvas canvas;

    // Required to use canvas
    private SurfaceHolder surfaceHolder;

    // Some paint for our canvas
    private Paint paint;

    public GameScene(Context context) {
        super(context);
    }

    public GameScene(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameScene(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public GameScene(Context context, Point size) {
        super(context);

        context = context;

        screenX = size.x;
        screenY = size.y;

        // Work out how many pixels each block is
        int blockSize = screenX / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        numBlocksHigh = screenY / blockSize;


        // Initialize the drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();


        // Start the game
        start();
    }

    public void run() {

        while (isPlaying) {

            // Update 10 times a second
            if(updateRequired()) {
                update();
                draw();
            }

        }
    }

    public void pause() {
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }


    @Override
    public void start() {
        // Start with a single snake segment
        worm = new Worm(screenX / NUM_BLOCKS_WIDE, new Point(NUM_BLOCKS_WIDE/2, numBlocksHigh/2));

        // Get Bob ready for dinner
        spawnBob();

        // Reset the score
        score = 0;

        // Setup nextFrameTime so an update is triggered
        nextFrameTime = System.currentTimeMillis();
    }

    public void spawnBob() {
        Random random = new Random();
        bobX = random.nextInt(NUM_BLOCKS_WIDE - 1) + 1;
        bobY = random.nextInt(numBlocksHigh - 1) + 1;
    }

    private void eatBob(){
        //  Got him!
        // Increase the size of the snake
        //snakeLength++;
        //replace Bob
        // This reminds me of Edge of Tomorrow. Oneday Bob will be ready!
        spawnBob();
        //add to the score
        score = score + 1;

    }



    public void update() {
        // Did the head of the snake eat Bob?
        if (worm.getHead().x == bobX && worm.getHead().y == bobY) {
            eatBob();
        }

        worm.move(heading);

        if (worm.isDead(new Rect(0, 0, NUM_BLOCKS_WIDE, numBlocksHigh))) {
            //start again
            start();
        }
    }

    public void draw() {
        // Get a lock on the canvas
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();

            // Fill the screen with Game Code School blue
            canvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the color of the paint to draw the snake white
            paint.setColor(Color.argb(255, 255, 255, 255));

            // Scale the HUD text
            paint.setTextSize(90);
            canvas.drawText("Score:" + score, 10, 70, paint);

            // Draw the snake one block at a time
            worm.draw(canvas, paint);

            // Set the color of the paint to draw Bob red
            paint.setColor(Color.argb(255, 255, 0, 0));

            // Draw Bob
            canvas.drawRect(bobX * worm.getSize(),
                    (bobY * worm.getSize()),
                    (bobX * worm.getSize()) + worm.getSize(),
                    (bobY * worm.getSize()) + worm.getSize(),
                    paint);

            // Unlock the canvas and reveal the graphics for this frame
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public boolean updateRequired() {

        // Are we due to update the frame
        if(nextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            nextFrameTime =System.currentTimeMillis() + MILLIS_PER_SECOND / FPS;

            // Return true so that the update and draw
            // functions are executed
            return true;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (motionEvent.getX() >= screenX / 2) {
                    switch(heading){
                        case UP:
                            heading = Heading.RIGHT;
                            break;
                        case RIGHT:
                            heading = Heading.DOWN;
                            break;
                        case DOWN:
                            heading = Heading.LEFT;
                            break;
                        case LEFT:
                            heading = Heading.UP;
                            break;
                    }
                } else {
                    switch(heading){
                        case UP:
                            heading = Heading.LEFT;
                            break;
                        case LEFT:
                            heading = Heading.DOWN;
                            break;
                        case DOWN:
                            heading = Heading.RIGHT;
                            break;
                        case RIGHT:
                            heading = Heading.UP;
                            break;
                    }
                }
        }
        return true;
    }

}
