package example.Model;

import example.Controller.StartFrameController;
import example.GameUtil;
import example.MusicPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StartFrameMain extends Application {

    private final int STAGE_WIDTH = 860;
    private final int STAGE_HEIGHT = 495;

    private static Scene scene;
    private static MusicPlayer currenMusic;


    @Override
    public void start(Stage primaryStage) throws IOException {
        scene = new Scene(loadFXML("/cw1setup/StartFrame"), STAGE_WIDTH, STAGE_HEIGHT);
        //Fetch CSS file
        String css = this.getClass().getResource("/cw1setup/application.css").toExternalForm();
        //Sets the  CSS file to this scene
        scene.getStylesheets().add(css);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(GameUtil.class.getResourceAsStream("/main/resources/cw1setup/Img/snake-logo.png")));
        //Play main menu music
        currenMusic = new MusicPlayer("src/main/resources/cw1setup/Sounds/main-menu-music.mp3");
        currenMusic.play();
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            primaryStage.close();
            System.exit(0);
        });
    }

    //Changes the root whenever a scene changes
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    //Changes the background music for the game
    public static void changeMusic(MusicPlayer newMusic) throws IOException {
        currenMusic.stopMusic();
        setCurrenMusic(newMusic);
        currenMusic.play();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartFrameMain.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setCurrenMusic(MusicPlayer currenMusic) {
        StartFrameMain.currenMusic = currenMusic;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
