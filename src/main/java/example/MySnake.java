package example;

import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class MySnake extends SnakeObject implements movable
{
    // The game changer.
    private int speed_XY;
    private int length;
    private int num; // ?
    public int score = 0; //K

    private static final BufferedImage IMG_SNAKE_HEAD = (BufferedImage) ImageUtil.images.get("snake-head-right");

    public static List<Point> bodyPoints = new LinkedList<>();

    private static BufferedImage newImgSnakeHead;
    boolean up, down, left, right = true;

    public MySnake(int x, int y)
    {
        this.live_of_object = true;
        this.x_position = x;
        this.y_position = y;
        this.i =
        //this.i = ImageUtil.images.get("snake-body");
        this.width_of_snake = i.getWidth(null);
        this.height_of_snake = i.getHeight(null);

        this.speed_XY = 5; //Starting speed is 5
        this.length = 1; //Starting length is 1

        /*
         * Attention : ?
         */
        this.num = width_of_snake / speed_XY;
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
        switch (e)
        {
            case .VK_UP:
                if (!down)
                {
                    up = true;
                    down = false;
                    left = false;
                    right = false;

                    newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);
                }
                break;

            case KeyEvent.VK_DOWN:
                if (!up)
                {
                    up = false;
                    down = true;
                    left = false;
                    right = false;

                    newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);
                }
                break;

            case KeyEvent.VK_LEFT:
                if (!right)
                {
                    up = false;
                    down = false;
                    left = true;
                    right = false;

                    newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -180);

                }
                break;

            case KeyEvent.VK_RIGHT:
                if (!left)
                {
                    up = false;
                    down = false;
                    left = false;
                    right = true;

                    newImgSnakeHead = IMG_SNAKE_HEAD;
                }

            default:
                break;
        }
    }


    public void move()
    {
        // Let the snake move
        if (up)
        {
            y_position -= speed_XY;
        } else if (down)
        {
            y_position += speed_XY;
        } else if (left)
        {
            x_position -= speed_XY;
        } else if (right)
        {
            x_position += speed_XY;
        }

    }

    @Override
    public void draw(Graphics g)
    {
        outofBounds();
        eatBody();

        bodyPoints.add(new Point(x_position, y_position));

        if (bodyPoints.size() == (this.length + 1) * num)
        {
            bodyPoints.remove(0);
        }
        g.drawImage(newImgSnakeHead, x_position, y_position, null);
        drawBody(g);

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
                    this.live_of_object = false;
                }
            }
        }
    }

    public void drawBody(Graphics g)
    {
        int length = bodyPoints.size() - 1 - num;

        for (int i = length; i >= num; i -= num)
        {
            Point point = bodyPoints.get(i);
            g.drawImage(this.i, point.x, point.y, null);
        }
    }

    private void outofBounds()
    {
        boolean xOut = (x_position <= 0 || x_position >= (870 - width_of_snake));
        boolean yOut = (y_position <= 40 || y_position >= (560 - height_of_snake));
        if (xOut || yOut)
        {
            live_of_object = false;
        }
    }
}
