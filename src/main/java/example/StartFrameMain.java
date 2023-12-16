package example;

import example.Controller.MyFrameController;
import example.Model.GameUtil;
import example.Model.MusicPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StartFrameMain extends Application {

    public static final int STAGE_WIDTH = 860;
    public static final int STAGE_HEIGHT = 495;

    private static Scene scene;

    private static MusicPlayer currenMusic;
    private static FXMLLoader loader;
    private static String currentMode; // Indicating the mode of the current game
    private static String playerName;

    @Override
    public void start(Stage primaryStage) throws IOException {
        scene = new Scene(loadFXML("/cw1setup/StartFrame"), STAGE_WIDTH, STAGE_HEIGHT);
        //Fetch CSS file
        String css = this.getClass().getResource("/cw1setup/application.css").toExternalForm();
        //Sets the  CSS file to this scene
        scene.getStylesheets().add(css);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(GameUtil.class.getResourceAsStream("/cw1setup/Img/snake-logo.png")));
        //Play main menu music
        currenMusic = new MusicPlayer("src/main/resources/cw1setup/Sounds/main-menu-music.mp3");
        currenMusic.play();
        primaryStage.setResizable(false);
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
    public static void changeMusic(MusicPlayer newMusic) {
        currenMusic.stopMusic();
        setCurrenMusic(newMusic);
        currenMusic.play();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        loader = new FXMLLoader(StartFrameMain.class.getResource(fxml + ".fxml"));
        return loader.load();
    }

    public static void setCurrenMusic(MusicPlayer currenMusic) {
        StartFrameMain.currenMusic = currenMusic;
    }
    public static MusicPlayer getCurrenMusic() {
        return currenMusic;
    }

    public static String getCurrentMode() {
        return currentMode;
    }

    public static void setCurrentMode(String currentMode) {
        StartFrameMain.currentMode = currentMode;
    }

    public static Scene getScene() {
        return scene;
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        StartFrameMain.playerName = playerName;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
