package cs105.w7.yoobeeworm;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import cs105.w7.yoobeeworm.game.GameScene;

public class MainActivity extends AppCompatActivity {

    GameScene gameScene;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the pixel dimensions of the screen
        Display display = getWindowManager().getDefaultDisplay();

        // Initialize the result into a Point object
        Point size = new Point();
        display.getSize(size);

        gameScene = new GameScene(this, size);
        setContentView(gameScene);
    }


    // Start the thread in snakeEngine
    @Override
    protected void onResume() {
        super.onResume();
        gameScene.resume();
    }

    // Stop the thread in snakeEngine
    @Override
    protected void onPause() {
        super.onPause();
        gameScene.pause();
    }

}
