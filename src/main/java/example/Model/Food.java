package example.Model;

import java.util.Random;

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
	 * @param mySnake The Snake object that interacts with the food.
	 */
	public void eaten(MySnake mySnake)	{

		//If the snake object touches the food object
		if (mySnake.getRectangle().intersects(this.getRectangle()) && liveOfObject && mySnake.liveOfObject)		{
			this.liveOfObject = false;
			mySnake.changeLength(mySnake.getLength() + 1); //Increase the body length of Snake
			int scoreIncrement = 1;
			mySnake.score += scoreIncrement; //Increments score
			//Plays Snake munching sound effect
			MusicPlayer.playSoundEffect("snake-eat-sound.mp3");
		}
	}
	@Override
	public void draw(GraphicsContext g)
	{
		g.drawImage(foodImg, xPosition, yPosition);
	}
}
