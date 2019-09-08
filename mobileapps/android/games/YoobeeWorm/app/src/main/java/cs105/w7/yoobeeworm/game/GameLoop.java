package cs105.w7.yoobeeworm.game;

public interface GameLoop {
    void resume();
    void start();
    void pause();
    void update();
    void draw();
}
