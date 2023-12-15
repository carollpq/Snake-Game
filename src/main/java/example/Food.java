package example;

import java.util.Random;

import example.Model.ImageUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;

public class Food extends SnakeObject
{
	private final int SCORE_INCREMENT = 1;
	public Food()	{
		this.liveOfObject = true;
		//Randomly obtain image for Food object
		this.foodImg = ImageUtil.images.get(String.valueOf(new Random().nextInt(17)));
		this.widthOfObj = (int) foodImg.getWidth();
		this.heightOfObj = (int) foodImg.getHeight();
		//Generates coordinates for Food object randomly
		this.xPosition = (int) (Math.random() * (860 - widthOfObj - 30) + 30);
		this.yPosition = (int) (Math.random() * (495 - heightOfObj - 60) + 55);
	}

	public void eaten(MySnake mySnake)	{

		//If the snake object touches the food object
		if (mySnake.getRectangle().intersects(this.getRectangle()) && liveOfObject && mySnake.liveOfObject)		{
			this.liveOfObject = false;
			mySnake.changeLength(mySnake.getLength() + 1); //Increase the body length of Snake
			mySnake.score += SCORE_INCREMENT; //Increments score
			//Plays Snake munching sound effect
			new AudioClip(
					getClass()
							.getResource("/cw1setup/Sounds/snake-eat-sound.mp3")
							.toExternalForm())
					.play();
		}
	}
	@Override
	public void draw(GraphicsContext g)
	{
		g.drawImage(foodImg, xPosition, yPosition);
	}
}
