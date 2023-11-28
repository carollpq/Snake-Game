package example;

import javafx.scene.input.KeyCode;

import java.util.LinkedList;
import java.util.List;

import java.awt.*;
import javafx.scene.input.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.canvas.GraphicsContext;

public class MySnake extends SnakeObject implements movable
{
    // The game changer.
    private int speed_XY;
    private int length;
    private int num; // ?
    public int score = 0; //K

    private static final Image IMG_SNAKE_HEAD = ImageUtil.images.get("snake-head-right");

    public static List<Point> bodyPoints = new LinkedList<>();

    private static Image newImgSnakeHead;
    boolean up, down, left, right = true;

    public MySnake(int x, int y)
    {
        this.liveOfObject = true;
        this.xPosition = x;
        this.yPosition = y;
        this.snakeBodyImg = ImageUtil.images.get("snake-body");
        this.widthOfSnake = (int)snakeBodyImg.getWidth(); //DONE: remove unnecessary parameter ; type-casted to correct type
        this.heightOfSnake = (int)snakeBodyImg.getHeight();

        this.speed_XY = 2; //Starting speed is 5
        this.length = 1; //Starting length is 1

        /*
         * Attention : ?
         */
        this.num = widthOfSnake / speed_XY;
        newImgSnakeHead = IMG_SNAKE_HEAD;

    }

    public int getLength()
    {
        return length;
    }

    public void changeLength(int length)
    {
        this.length = length;
    }

    public void handleKeyPress(javafx.scene.input.KeyEvent e)
    {
        // Checking the keys
        switch (e.getCode())
        {
            case UP: //DONE: change cases to 'KeyCode' constants
                //DONE: removed unnecessary and redundant boolean assignment
                if (!down)
                {
                    up = true;
                    left = false;
                    right = false;

                    newImgSnakeHead = GameUtil.rotateImage(IMG_SNAKE_HEAD, -90).getImage();
                }
                break;

            case DOWN:
                if (!up)
                {
                    down = true;
                    left = false;
                    right = false;

                    newImgSnakeHead = GameUtil.rotateImage(IMG_SNAKE_HEAD, 90).getImage();
                }
                break;

            case LEFT:
                if (!right)
                {
                    up = false;
                    down = false;
                    left = true;

                    newImgSnakeHead = GameUtil.rotateImage(IMG_SNAKE_HEAD, -180).getImage();

                }
                break;

            case RIGHT:
                if (!left)
                {
                    up = false;
                    down = false;
                    right = true;

                    newImgSnakeHead = IMG_SNAKE_HEAD;
                }
                break; //DONE: added missing break statement

            default:
                break;
        }
    }


    public void move()
    {
        // Let the snake move
        if (up)
        {
            yPosition -= speed_XY;
        } else if (down)
        {
            yPosition += speed_XY;
        } else if (left)
        {
            xPosition -= speed_XY;
        } else if (right)
        {
            xPosition += speed_XY;
        }

    }

    public void draw(GraphicsContext gc)
    {
        outofBounds();
        eatBody();

        bodyPoints.add(new Point(xPosition, yPosition));

        if (bodyPoints.size() == (this.length + 1) * num)
        {
            bodyPoints.remove(0);
        }
        gc.drawImage(newImgSnakeHead, xPosition, yPosition);
        drawBody(gc);

        move();
    }

    public void eatBody()
    {
        for (Point point : bodyPoints)
        {
            for (Point point2 : bodyPoints)
            {
                if (point.equals(point2) && point != point2)
                {
                    this.liveOfObject = false;
                }
            }
        }
    }

    public void drawBody(GraphicsContext g)
    {
        int length = bodyPoints.size() - 1 - num;

        for (int i = length; i >= num; i -= num)
        {
            Point point = bodyPoints.get(i);
            g.drawImage(this.snakeBodyImg, point.x, point.y); //DONE: remove unused parameter
        }
    }

    private void outofBounds()
    {
        boolean xOut = (xPosition <= 0 || xPosition >= (870 - widthOfSnake));
        boolean yOut = (yPosition <= 40 || yPosition >= (560 - heightOfSnake));
        if (xOut || yOut)
        {
            liveOfObject = false;
        }
    }
}