package example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StartFrameMain extends Application {

    private final int STAGE_WIDTH = 870;
    private final int STAGE_HEIGHT = 560;
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/cw1setup/StartFrame.fxml"));
        Scene startScene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        //Fetch CSS file
        String css = this.getClass().getResource("/main/java/example/application.css").toExternalForm();
        //Sets the  CSS file to this scene
        startScene.getStylesheets().add(css);
        primaryStage.setTitle("Snake Game");

        primaryStage.setScene(startScene);
        primaryStage.getIcons().add(new Image(GameUtil.class.getResourceAsStream("/main/resources/cw1setup/Img/snake-logo.png")));
        primaryStage.show();
        MusicPlayer.getMusicPlay("src/main/resources/cw1setup/Sounds/main-menu-sound.mp3");

        primaryStage.setOnCloseRequest(e -> {
            primaryStage.close();
            System.exit(0); //DONE: properly exiting the program
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
