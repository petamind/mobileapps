package com.example.tungnguyen.asurvivorgame;

import android.graphics.Bitmap;

public class Sprite { //////////////////////////////////////////////////////////////////////////////////////////////// //Step 1: Declare properties/data of the class
    protected Bitmap image;
    //Character image
    protected final int rowCount;
    protected final int colCount;
    protected final int WIDTH;
    protected final int HEIGHT;
    protected final int width;
    protected final int height;
    protected int x;
    protected int y;
    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 2: Constructor
    public Sprite(Bitmap image, int rowCount, int colCount, int x, int y) {
        this.image = image; this.rowCount = rowCount; this.colCount = colCount; this.x = x;
        this.y = y;
        this.WIDTH = image.getWidth(); this.HEIGHT = image.getHeight(); this.width = this.WIDTH / colCount; this.height = this.HEIGHT / rowCount;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 3: Create a sprite sub-image at the position (row,col)
    protected Bitmap createSubImageAt(int row, int col) {
        Bitmap subImage = Bitmap.createBitmap(image, col * width, row * height, width, height);
        return subImage; }
    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 3: Read the coordinate x of sprite
    public int getX() {
        return this.x; }
    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 4: Read the coordinate y of sprite
    public int getY() {
        return this.y; }
    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 5: Get the sprite image height
    public int getHeight() {
        return height; }
    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 5: Get the sprite image width
    public int getWidth() {
        return width; }
    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 6: Set the coordinate X
    public void setX(int x) {
        this.x = x; }
    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 7: Set the coordinate Y
    public void setY(int y) {
        this.y = y; }
}

