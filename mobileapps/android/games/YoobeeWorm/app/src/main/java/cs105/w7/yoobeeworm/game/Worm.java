package cs105.w7.yoobeeworm.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;

import java.util.ArrayList;

public class Worm  {
    private ArrayList<Point> slither;
    private Point head;

    /**
     * Size of the body (radius)OOOOOO<>
     */
    private int size;

    public Worm(int size, Point head){
        this.size = size;
        this.head = head;
        slither = new ArrayList<>();
        slither.add(head);
    }

    public void draw (Canvas canvas, Paint paint){
        drawHead(canvas, paint);
        drawBody(canvas, paint);
    }

    private void drawHead(Canvas canvas, Paint paint) {
        int halfWidth = size/2 ;

        Path path = new Path();
        path.moveTo(head.x * size, head.y* size + halfWidth); // Top
        path.lineTo(head.x* size - halfWidth, head.y* size); // Left
        path.lineTo(head.x* size, head.y* size - halfWidth); // Bottom
        path.lineTo(head.x* size + halfWidth, head.y* size); // Right
        path.lineTo(head.x* size, head.y* size + halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }

    private void drawBody(Canvas canvas, Paint paint){
        for (int i = 1; i < slither.size(); i++) {
            canvas.drawRect(slither.get(i).x * size,
                    (slither.get(i).y * size),
                    (slither.get(i).x * size) + size,
                    (slither.get(i).y * size) + size,
                    paint);
        }
    }

    public int getSize() {
        return size;
    }

    public Point getHead() {
        return head;
    }

    public void setHead(Point head) {
        this.head = head;
    }

    public void move(Heading heading){
        // Move the head in the appropriate heading
        switch (heading) {
            case UP:
                head.y--;
                break;

            case RIGHT:
                head.x++;
                break;

            case DOWN:
                head.y++;
                break;

            case LEFT:
                head.x--;
                break;
        }

        // Move the body
        for (int i = this.slither.size()-1; i > 0; i--) {
            // Start at the back and move it
            // to the position of the segment in front of it
            slither.get(i).set(slither.get(i-1).x, slither.get(i-1).y);

            // Exclude the head because
            // the head has nothing in front of it
        }


    }

    public boolean isDead(Rect border){
        // Has the snake died?
        boolean dead = false;

        // Hit the screen edge
        if (head.x == -1 ||
                head.x >= border.width()
                || head.y == -1 || head.y == border.height()){
            dead = true;
        }

        // Eaten itself?
        for (int i = slither.size() - 1; i > 0; i--) {
            if ((i > 4) && (head .equals(slither.get(i)))) {
                dead = true;
            }
        }

        return dead;
    }
}
