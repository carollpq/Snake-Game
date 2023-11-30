package example;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * 
 * @Project Snakee
 * @Description Load the game and update it continuously
 * @Author Sigurður Sigurðardóttir
 * @version Not sure
 */ 


public class MyFrame extends Application implements Frame {

	protected GraphicsContext graphicsContext;

	public MySnake mySnake;

	public Food food = new Food();

	public Image background = ImageUtil.images.get("UI-background");
	public Image fail = ImageUtil.images.get("game-scene-01");

	public void drawScore(GraphicsContext gc)
	{
		Font font = Font.font("Sans Serif", FontWeight.BOLD, FontPosture.REGULAR, 30);
		gc.setFont(font);
		gc.setFill(Color.MAGENTA);
		gc.fillText("SCORE : " + mySnake.score, 20, 40);
	}

	@Override
	public void drawBgImg(GraphicsContext gc)
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

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Snake Game");
		primaryStage.getIcons().add(new Image(GameUtil.class.getResourceAsStream("/main/resources/cw1setup/Img/snake-logo.png")));

		Canvas canvas = new Canvas(STAGE_WIDTH, STAGE_HEIGHT);
		//Returns GraphicsContext -> draw shapes, text, and images onto Canvas
		graphicsContext = canvas.getGraphicsContext2D();

		StackPane layout = new StackPane();
		layout.getChildren().add(canvas);

		Scene scene = new Scene(layout, STAGE_WIDTH, STAGE_HEIGHT);

		mySnake = new MySnake(100, 100);// x , y

		scene.setOnKeyPressed(e -> {
			mySnake.handleKeyPress(e);
		});

		primaryStage.setScene(scene);
		primaryStage.show();

		MusicPlayer.getMusicPlay("src/main/resources/cw1setup/Sounds/frogger.mp3");

		primaryStage.setOnCloseRequest(e -> {
			primaryStage.close();
			System.exit(0); //DONE: properly exiting the program
		});
	}
}
