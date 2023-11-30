package example;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
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
				drawBgImg(graphicsContext);
			}
		};

		animationTimer.start();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
