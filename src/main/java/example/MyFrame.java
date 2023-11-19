package example;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
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


public class MyFrame extends Application {
	//private static final long serialVersionUID = -3149926831770554380L;
	private static final int STAGE_WIDTH = 870;
	private static final int STAGE_HEIGHT = 560;

	public GraphicsContext graphicsContext;

	public MySnake mySnake;

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Snake Game");
		primaryStage.getIcons().add(new Image(GameUtil.class.getResourceAsStream("/main/java/example/snake-logo.png")));

		Canvas canvas = new Canvas(STAGE_WIDTH, STAGE_HEIGHT);
		//Returns GraphicsContext -> draw shapes, text, and images onto Canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();
		graphicsContext = gc;

		StackPane layout = new StackPane();
		layout.getChildren().add(canvas);

		Scene scene = new Scene(layout, STAGE_WIDTH, STAGE_HEIGHT);

		mySnake = new MySnake(100, 100);// x , y

		scene.setOnKeyPressed(e -> {
			mySnake.handleKeyPress(e);
		});

		primaryStage.setScene(scene);
		primaryStage.show();

		MusicPlayer.getMusicPlay("src/main/java/example/frogger.mp3");

		primaryStage.setOnCloseRequest(e -> {
			primaryStage.close();
			System.exit(0); //DONE: properly exiting the program
		});
	}
}
