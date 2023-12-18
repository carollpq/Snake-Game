package example.Model;

import java.util.LinkedList;
import java.util.List;

import java.awt.*;

import example.StartFrameMain;
import example.movable;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;

import javafx.scene.canvas.GraphicsContext;


/**
 * The MySnake class represents the player-controlled snake in the game.
 * It extends the SnakeObject class and implements the movable interface for handling movements.
 *
 * <p>The snake's properties include its position, speed, length, and body points.
 * The class provides methods for handling key presses to change the snake's direction,
 * updating the snake's movement, drawing the snake on the game canvas, and checking for collisions.</p>
 *
 * @author Carolina Lee Pei Qian
 * @version 1.0
 */
public class MySnake extends SnakeObject implements movable
{
    private int speed_XY;
    int length;
    private int num;
    public int score = 0;

    //Image of the Snake's head
    private static final Image IMG_SNAKE_HEAD = ImageUtil.images.get("snake-head-right");
    //Stores all the coordinates where the snake has traversed
    public static List<Point> bodyPoints = new LinkedList<>();


    private static Image newImgSnakeHead;
    boolean up, down, left, right = true;


    /**
     * Constructor for creating a MySnake object with the specified position and speed.
     *
     * @param x           The initial x-coordinate of the snake.
     * @param y           The initial y-coordinate of the snake.
     * @param snakeSpeed  The speed of the snake.
     */
    public MySnake(int x, int y, int snakeSpeed)
    {
        this.liveOfObject = true;
        this.xPosition = x;
        this.yPosition = y;
        this.snakeBodyImg = ImageUtil.images.get("snake-body");
        this.widthOfObj = (int)snakeBodyImg.getWidth();
        this.heightOfObj = (int)snakeBodyImg.getHeight();

        setSpeed_XY(snakeSpeed);
        this.length = 1; //Starting length is 1

        this.num = widthOfObj / speed_XY;
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

    /**
     * Handles key presses for changing the snake's direction if player chooses right controls.
        *
        * @param e The KeyEvent representing the key press.
     */
    public void handleKeyPressRight(KeyEvent e)
    {
        // Checking the keys
        switch (e.getCode())
        {
            case UP:
                handleSnakeMoveUp();
                break;
            case DOWN:
                handleSnakeMoveDown();
                break;
            case LEFT:
                handleSnakeMoveLeft();
                break;
            case RIGHT:
                handleSnakeMoveRight();
                break;
            default:
                break;
        }
    }

    /**
     * Handles key presses for changing the snake's direction if player chooses left controls.
     *
     * @param e The KeyEvent representing the key press.
     */
    public void handleKeyPressLeft(KeyEvent e)
    {
        // Checking the keys
        switch (e.getCode())
        {
            case W:
                handleSnakeMoveUp();
                break;
            case S:
                handleSnakeMoveDown();
                break;
            case A:
                handleSnakeMoveLeft();
                break;
            case D:
                handleSnakeMoveRight();
                break;
            default:
                break;
        }
    }

    //Sets the condition for snake to move left
    public void handleSnakeMoveLeft() {
        if (!right)
        {
            up = false;
            down = false;
            left = true;
            newImgSnakeHead = ImageUtil.images.get("snake-head-left");
        }
    }

    //Sets the condition for snake to move up
    public void handleSnakeMoveUp() {
        if (!down)
        {
            up = true;
            left = false;
            right = false;
            newImgSnakeHead = ImageUtil.images.get("snake-head-up");
        }
    }

    //Sets the condition for snake to move down
    public void handleSnakeMoveDown() {
        if (!up)
        {
            down = true;
            left = false;
            right = false;
            newImgSnakeHead = ImageUtil.images.get("snake-head-down");
        }
    }

    //Sets the condition for snake to move right
    public void handleSnakeMoveRight() {
        if (!left)
        {
            up = false;
            down = false;
            right = true;

            newImgSnakeHead = IMG_SNAKE_HEAD;
        }
    }

    //Handles Snake's movement direction
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

        outOfBounds();
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

    //Checks whether the Snake has eaten itself
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

    /**
     * Draws the snake's body on the canvas.
     *
     * @param g The GraphicsContext used for drawing on the canvas.
     */
    public void drawBody(GraphicsContext g)
    {
        int length = bodyPoints.size() - 1 - num;

        for (int i = length; i >= num; i -= num)
        {
            Point point = bodyPoints.get(i);
            g.drawImage(this.snakeBodyImg, point.x, point.y);
        }
    }

    public void setSpeed_XY(int speed_XY) {
        this.speed_XY = speed_XY;
    }

    //Checks whether the Snake has gone out of bounds
    private void outOfBounds()
    {
        boolean xOut = (xPosition <= 0 || xPosition >= (StartFrameMain.STAGE_WIDTH - widthOfObj));
        boolean yOut = (yPosition <= 0 || yPosition >= (StartFrameMain.STAGE_HEIGHT - heightOfObj));
        if (xOut || yOut)
        {
            liveOfObject = false;
        }
    }
}
