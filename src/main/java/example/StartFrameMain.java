package example;

import example.Model.Utilities.GameUtil;
import example.Model.Utilities.ImageUtil;
import example.Model.Utilities.MusicPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Main class for the Snake Game application. Extends the JavaFX Application class
 * and serves as the entry point for launching the game.
 *
 * The game allows the player to navigate through different scenes, such as the main menu and
 * gameplay scenes, while providing features like customizable player names, game modes, and music.
 *
 * @author Carolina Lee Pei Qian
 * @version 1.0
 */
public class StartFrameMain extends Application {

    public static final int STAGE_WIDTH = 860;
    public static final int STAGE_HEIGHT = 495;

    private static Scene scene;

    private static MusicPlayer currenMusic;
    private static FXMLLoader loader;
    private static String currentMode; // Indicating the mode of the current game
    private static String playerName;

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

    /**
     * The entry point for launching the application. Initializes the game images, sets up the main menu scene,
     * and starts the primary stage.
     *
     * @param primaryStage The primary stage for the application.
     * @throws IOException If an I/O error occurs during application startup.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        ImageUtil.initializeImages(); //Singleton pattern
        scene = new Scene(loadFXML("/StartFrame"), STAGE_WIDTH, STAGE_HEIGHT);
        //Fetch CSS file
        String css = this.getClass().getResource("/application.css").toExternalForm();
        //Sets the  CSS file to this scene
        scene.getStylesheets().add(css);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(GameUtil.class.getResourceAsStream("/Img/snake-logo.png")));
        //Play main menu music
        currenMusic = new MusicPlayer("src/main/resources/Sounds/main-menu-music.mp3");
        currenMusic.play();
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            primaryStage.close();
            System.exit(0);
        });
    }

    /**
     * Changes the root of the current scene to the specified FXML file, effectively changing the displayed content.
     *
     * @param fxml The name of the FXML file to load.
     * @throws IOException If an I/O error occurs during the FXML loading process.
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Changes the background music for the game.
     *
     * @param newMusic The MusicPlayer object representing the new music to play.
     */
    public static void changeMusic(MusicPlayer newMusic) {
        currenMusic.stopMusic();
        setCurrenMusic(newMusic);
        currenMusic.play();
    }

    /**
     * Loads the FXML content from the specified file and returns a Parent object representing the root of the scene.
     *
     * @param fxml The name of the FXML file (excluding the file extension).
     * @return The Parent object representing the root of the scene.
     * @throws IOException If an I/O error occurs during the FXML loading process.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        loader = new FXMLLoader(StartFrameMain.class.getResource(fxml + ".fxml"));
        return loader.load();
    }

    /**
     * The main method that launches the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
