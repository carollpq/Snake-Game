package com.SnakeGame.Model.GameObjects;

import com.SnakeGame.StartFrameMain;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;


/**
 * Abstract class representing objects that will be drawn onto the game canvas.
 */
public abstract class SnakeObject
{
    private int xPosition;

    private int yPosition;

    private int widthOfObj;

    private int heightOfObj;
    Image snakeBodyImg;

    private Image foodImg;

    private boolean liveOfObject;
    final int HORIZONTAL_BUFFER = 30;
    final int VERTICAL_BUFFER = 60;
    final int HORIZONTAL_SHIFT = 30;
    final int VERTICAL_SHIFT = 55;

    /**
     * Gets the X-coordinate of the snake object's position.
     *
     * @return The X-coordinate of the snake object's position.
     */

    public int getXPosition() {
        return xPosition;
    }

    /**
     * Sets the X-coordinate of the snake object's position.
     *
     * @param xPosition The new X-coordinate of the snake object's position.
     */
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Gets the Y-coordinate of the snake object's position.
     *
     * @return The Y-coordinate of the snake object's position.
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Sets the Y-coordinate of the snake object's position.
     *
     * @param yPosition The new Y-coordinate of the snake object's position.
     */
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * Gets the width of the snake object.
     *
     * @return The width of the snake object.
     */
    public int getWidthOfObj() {
        return widthOfObj;
    }

    /**
     * Sets the width of the snake object.
     *
     * @param widthOfObj The new width of the snake object.
     */
    public void setWidthOfObj(int widthOfObj) {
        this.widthOfObj = widthOfObj;
    }


    /**
     * Gets the height of the snake object.
     *
     * @return The height of the snake object.
     */
    public int getHeightOfObj() {
        return heightOfObj;
    }


    /**
     * Sets the height of the snake object.
     *
     * @param heightOfObj The new height of the snake object.
     */

    public void setHeightOfObj(int heightOfObj) {
        this.heightOfObj = heightOfObj;
    }


    /**
     * Gets the image representing the food object.
     *
     * @return The image representing the food object.
     */
    public Image getFoodImg() {
        return foodImg;
    }


    /**
     * Sets the image representing the food object.
     *
     * @param foodImg The new image representing the food object.
     */
    public void setFoodImg(Image foodImg) {
        this.foodImg = foodImg;
    }


    /**
     * Checks the liveliness status of the snake object.
     *
     * @return True if the snake object is alive, false otherwise.
     */
    public boolean isLiveOfObject() {
        return liveOfObject;
    }

    /**
     * Sets the liveliness status of the snake object.
     *
     * @param liveOfObject The new liveliness status of the snake object.
     */
    public void setLiveOfObject(boolean liveOfObject) {
        this.liveOfObject = liveOfObject;
    }

    /**
     * Draws the snake object on the game canvas using the provided GraphicsContext.
     *
     * @param gc The GraphicsContext used for drawing on the canvas.
     */
    public abstract void draw(GraphicsContext gc);

    /**
     * Retrieves the rectangle representing the bounds of the snake object.
     *
     * @return A Rectangle2D representing the bounds of the snake object.
     */
    public Rectangle2D getRectangle()
    {
        return new Rectangle2D(xPosition, yPosition, widthOfObj, heightOfObj);
    }


    /**
     * Generates a random X position within the specified bounds for the snake object.
     *
     * @return A random X position for the snake object.
     */
    public int generateRandomXPosition() {
        return (int) (Math.random() * (StartFrameMain.STAGE_WIDTH - widthOfObj - HORIZONTAL_BUFFER) + HORIZONTAL_SHIFT);
    }


    /**
     * Generates a random Y position within the specified bounds for the snake object.
     *
     * @return A random Y position for the snake object.
     */
    public int generateRandomYPosition() {
        return (int) (Math.random() * (StartFrameMain.STAGE_HEIGHT - heightOfObj - VERTICAL_BUFFER) + VERTICAL_SHIFT);
    }
}
