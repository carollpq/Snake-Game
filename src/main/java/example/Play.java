package example;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 
 * @Project Snakee
 * @Description Spilaðu leikinn
 * @Author Sigurður Sigurðardóttir
 * @version Ekki viss
 */ 

public class Play extends MyFrame
{

	private static final long serialVersionUID = -3641221053272056036L;
	public Food food = new Food();

	public Image background = ImageUtil.images.get("UI-background");
	public Image fail = ImageUtil.images.get("game-scene-01");

	public void draw(GraphicsContext gc)
	{
		//super.draw(gc);
		gc.drawImage(background, 0, 0);

		// Determine the state of the game.
		if (mySnake.liveOfObject)
		{
			mySnake.draw(gc);
			if (food.liveOfObject)
			{
				food.draw(gc);
				food.eaten(mySnake);
			} else
			{
				food = new Food();
			}
		} else
		{
			gc.drawImage(fail, 0, 0);
		}
		drawScore(gc);
	}

	public void drawScore(GraphicsContext gc)
	{
		Font font = Font.font("Sans Serif", FontWeight.BOLD, FontPosture.REGULAR, 30);
		gc.setFont(font);
		gc.setFill(Color.MAGENTA);
		gc.fillText("SCORE : " + mySnake.score, 20, 40);
	}
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		super.start(primaryStage);
		//We need to erase body, constantly check  eat fruit or not
		AnimationTimer animationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				//playFrame.draw(graphicsContext);
				mySnake.move();
				mySnake.draw(graphicsContext);
				draw(graphicsContext);
			}
		};

		animationTimer.start();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
