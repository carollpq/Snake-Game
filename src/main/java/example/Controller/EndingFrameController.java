package example.Controller;

import example.Model.ImageUtil;
import example.StartFrameMain;
import example.Model.MusicPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;


public class EndingFrameController extends StartFrameController {
    @FXML
    //Images in the 'Play Again' and 'Main Menu' buttons
    private ImageView playAgainBtnImg, mainMenuBtnImg;

    @FXML
    private Label scoreLabel;

    public void setScoreLabel(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    @FXML
    //Set the image of 'Play Again' button when it is not hovered
    public void onPlayAgain() {
        playAgainBtnImg.setImage(ImageUtil.images.get("play-again-btn"));
    }

    @FXML
    //Set the image of 'Play Again' button when it is hovered
    public void onPlayAgainHover() {
        playAgainBtnImg.setImage(ImageUtil.images.get("play-again-hover"));
    }

    @FXML
    //Set the image of 'Main Menu' button when it is not hovered
    public void onMainMenu() {
        mainMenuBtnImg.setImage(ImageUtil.images.get("main-menu-btn"));
    }

    @FXML
    //Set the image of 'Main Menu' button when it is hovered
    public void onMainMenuHover() {
        mainMenuBtnImg.setImage(ImageUtil.images.get("main-menu-hover"));
    }
    @FXML
    public void playAgain() throws IOException, InterruptedException {
        scoreLabel.setVisible(false); //Removes the label displaying high score
        if (Objects.equals(StartFrameMain.getCurrentMode(), "easy")) {
            playEasyMode();
        } else if (Objects.equals(StartFrameMain.getCurrentMode(), "medium")) {
            playMediumMode();
        } else if (Objects.equals(StartFrameMain.getCurrentMode(), "hard")) {
            playHardMode();
        }
    }

    @FXML
    public void initialize() {
        //Sets the text for label displaying final score when FXML file is loaded
        setScoreLabel(MyFrameController.getFinalScore());
    }

    @Override
    //Loads the Home page/Main Menu page after 'Main Menu' is clicked
    public void backToMainMenu() throws IOException {
        scoreLabel.setVisible(false); //Removes the label displaying high score
        super.backToMainMenu();
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/main-menu-music.mp3"));
    }
}
