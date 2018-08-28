package com.example.tungnguyen.asurvivorgame;

public class BoundingBox { //////////////////////////////////////////////////////////////////////////////////////////////// //Step 1: Declare properties/data of the class
    private int xMin;//The coordinates: (left, top, right, bottom)
    private int yMin;
    private int xMax;
    private int yMax;

    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 2: Constructor
    public BoundingBox(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 3: set() method - set the new bounding box size with (top, left, width, height)
    public void set(int x, int y, int width, int height) {
        xMin = x;
        xMax = x + width;
        yMin = y;
        yMax = y + height;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 4: Read the coordinate xMin
    public int getXmin() {
        return this.xMin;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 5: Read the coordinate xMax
    public int getXmax() {
        return this.xMax;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 6: Read the coordinate yMin
    public int getYmin() {
        return this.yMin;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////// //Step 7: Read the coordinate yMax
    public int getYmax() {
        return this.yMax;
    }
}
