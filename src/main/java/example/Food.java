package example;

import java.util.Random;

import example.Model.ImageUtil;
import example.Model.MusicPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;

public class Food extends SnakeObject
{
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
