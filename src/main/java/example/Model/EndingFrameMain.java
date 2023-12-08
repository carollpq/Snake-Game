package example.Model;

import example.Controller.StartFrameController;
import example.GameUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class EndingFrameMain extends Application {
    private final int STAGE_WIDTH = 870;
    private final int STAGE_HEIGHT = 560;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/cw1setup/EndingFrame.fxml"));
        Parent root = loader.load();

        StartFrameController controller = loader.getController();
        Scene endScene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
        //Fetch CSS file
        String css = this.getClass().getResource("/main/java/example/application.css").toExternalForm();
        //Sets the  CSS file to this scene
        endScene.getStylesheets().add(css);
        primaryStage.setScene(endScene);
        //Play main menu music
        controller.getCurrentMusic().play();
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            primaryStage.close();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
