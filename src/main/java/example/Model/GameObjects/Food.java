package example.Model.GameObjects;

import java.util.Random;

import example.Model.Utilities.ImageUtil;
import example.Model.Utilities.MusicPlayer;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a food object in the Snake game.
 */
public class Food extends SnakeObject
{
	/**
	 * Constructs a Food object with a random image, position, and sets it as alive.
	 */
	public Food()	{
		this.liveOfObject = true;
		//Randomly obtain image for Food object
		this.foodImg = ImageUtil.images.get(String.valueOf(new Random().nextInt(17)));
		this.widthOfObj = (int) foodImg.getWidth();
		this.heightOfObj = (int) foodImg.getHeight();
		//Generates coordinates for Food object randomly
		this.xPosition = generateRandomXPosition();
		this.yPosition = generateRandomYPosition();
	}

	/**
	 * Handles the logic when the Food object is eaten by the Snake.
	 *
	 * @param snake The Snake object that interacts with the food.
	 */
	public void eaten(Snake snake)	{

		//If the snake object touches the food object
		if (snake.getRectangle().intersects(this.getRectangle()) && liveOfObject && snake.liveOfObject)		{
			this.liveOfObject = false;
			snake.changeLength(snake.getLength() + 1); //Increase the body length of Snake
			int scoreIncrement = 1;
			snake.score += scoreIncrement; //Increments score
			//Plays Snake munching sound effect
			MusicPlayer.playSoundEffect("snake-eat-sound.mp3");
		}
	}

	/**
	 * Draws the Food object on the game canvas using the provided GraphicsContext.
	 *
	 * @param g The GraphicsContext used for drawing on the canvas.
	 */
	@Override
	public void draw(GraphicsContext g)
	{
		g.drawImage(foodImg, xPosition, yPosition);
	}
}
