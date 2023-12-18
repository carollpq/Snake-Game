package example.Model.GameObjects;

import java.util.LinkedList;
import java.util.List;

import java.awt.*;

import example.Model.Utilities.ImageUtil;
import example.StartFrameMain;
import example.movable;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;

import javafx.scene.canvas.GraphicsContext;


/**
 * The Snake class represents the player-controlled snake in the game.
 * It extends the SnakeObject class and implements the movable interface for handling movements.
 *
 * <p>The snake's properties include its position, speed, length, and body points.
 * The class provides methods for handling key presses to change the snake's direction,
 * updating the snake's movement, drawing the snake on the game canvas, and checking for collisions.</p>
 *
 * @author Carolina Lee Pei Qian
 * @version 1.0
 */
public class Snake extends SnakeObject implements movable
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
     * Constructor for creating a Snake object with the specified position and speed.
     *
     * @param x           The initial x-coordinate of the snake.
     * @param y           The initial y-coordinate of the snake.
     * @param snakeSpeed  The speed of the snake.
     */
    public Snake(int x, int y, int snakeSpeed)
    {
        setLiveOfObject(true);
        setXPosition(x);
        setYPosition(y);
        this.snakeBodyImg = ImageUtil.images.get("snake-body");
        setWidthOfObj((int)snakeBodyImg.getWidth());
        setHeightOfObj((int)snakeBodyImg.getHeight());

        setSpeed_XY(snakeSpeed);
        this.length = 1; //Starting length is 1

        this.num = getWidthOfObj() / speed_XY;
        newImgSnakeHead = IMG_SNAKE_HEAD;

    }

    /**
     * Gets the length of the snake.
     *
     * @return The length of the snake.
     */
    public int getLength()
    {
        return length;
    }

    /**
     * Changes the length of the snake.
     *
     * @param length The new length of the snake.
     */
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

    /**
     * Sets the condition for the snake to move left.
     */
    public void handleSnakeMoveLeft() {
        if (!right)
        {
            up = false;
            down = false;
            left = true;
            newImgSnakeHead = ImageUtil.images.get("snake-head-left");
        }
    }

    /**
     * Sets the condition for the snake to move up.
     */
    public void handleSnakeMoveUp() {
        if (!down)
        {
            up = true;
            left = false;
            right = false;
            newImgSnakeHead = ImageUtil.images.get("snake-head-up");
        }
    }

    /**
     * Sets the condition for the snake to move down.
     */
    public void handleSnakeMoveDown() {
        if (!up)
        {
            down = true;
            left = false;
            right = false;
            newImgSnakeHead = ImageUtil.images.get("snake-head-down");
        }
    }

    /**
     * Sets the condition for the snake to move right.
     */
    public void handleSnakeMoveRight() {
        if (!left)
        {
            up = false;
            down = false;
            right = true;

            newImgSnakeHead = IMG_SNAKE_HEAD;
        }
    }

    /**
     * Handles the snake's movement by updating its position.
     */
    public void move()
    {
        // Let the snake move
        if (up)
        {
            setYPosition(getYPosition() - speed_XY);
        } else if (down)
        {
            setYPosition(getYPosition() + speed_XY);
        } else if (left)
        {
            setXPosition(getXPosition() - speed_XY);
        } else if (right)
        {
            setXPosition(getXPosition() + speed_XY);
        }

    }

    /**
     * Draws the snake on the game canvas using the provided GraphicsContext.
     *
     * @param gc The GraphicsContext used for drawing on the canvas.
     */
    public void draw(GraphicsContext gc)
    {

        outOfBounds();
        eatBody();

        bodyPoints.add(new Point(getXPosition(), getYPosition()));

        if (bodyPoints.size() == (this.length + 1) * num)
        {
            bodyPoints.remove(0);
        }
        gc.drawImage(newImgSnakeHead, getXPosition(), getYPosition());
        drawBody(gc);

        move();
    }

    /**
     * Checks whether the snake has collided with itself.
     */
    public void eatBody()
    {
        for (Point point : bodyPoints)
        {
            for (Point point2 : bodyPoints)
            {
                if (point.equals(point2) && point != point2)
                {
                    setLiveOfObject(false);
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

    /**
     * Sets the speed of the snake in the X and Y directions.
     *
     * @param speed_XY The speed of the snake.
     */
    public void setSpeed_XY(int speed_XY) {
        this.speed_XY = speed_XY;
    }

    /**
     * Checks whether the Snake has gone out of bounds
     */
    private void outOfBounds()
    {
        boolean xOut = (getXPosition() <= 0 || getXPosition() >= (StartFrameMain.STAGE_WIDTH - getWidthOfObj()));
        boolean yOut = (getYPosition() <= 0 || getYPosition() >= (StartFrameMain.STAGE_HEIGHT - getHeightOfObj()));
        if (xOut || yOut)
        {
            setLiveOfObject(false);
        }
    }
}
