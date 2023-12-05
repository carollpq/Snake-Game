package example.Model;

import java.io.IOException;

import example.Controller.MyFrameController;
import example.GameUtil;
import example.MusicPlayer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 * @Project Snakee
 * @Description Load the game and update it continuously
 * @Author Sigurður Sigurðardóttir
 * @version Not sure
 */ 


public class MyFrameMain extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/cw1setup/MyFrame.fxml"));
		Parent root = loader.load();

		MyFrameController controller = loader.getController();
		primaryStage.setTitle("Snake Game");
		primaryStage.getIcons().add(new Image(GameUtil.class.getResourceAsStream("/main/resources/cw1setup/Img/snake-logo.png")));

		Scene scene = new Scene(root, 870, 560);

		scene.setOnKeyPressed(controller::handleKeyPress);

		primaryStage.setScene(scene);
		primaryStage.show();

		controller.initialize();
		controller.animationTimer();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

