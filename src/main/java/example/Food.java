package example;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;

public class Food extends SnakeObject
{
	private final int SCORE_INCREMENT = 1;
	public Food()	{
		this.liveOfObject = true;

		this.foodImg = ImageUtil.images.get(String.valueOf(new Random().nextInt(17)));
		this.widthOfSnake = (int) foodImg.getWidth();
		this.heightOfSnake = (int) foodImg.getHeight();

		this.xPosition = (int) (Math.random() * (860 - widthOfSnake - 30) + 30);
		this.yPosition = (int) (Math.random() * (495 - heightOfSnake - 60) + 55);
	}

	public void eaten(MySnake mySnake)	{

		//If the snake object touches the food object
		if (mySnake.getRectangle().intersects(this.getRectangle()) && liveOfObject && mySnake.liveOfObject)		{
			this.liveOfObject = false;
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.score += SCORE_INCREMENT;
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
