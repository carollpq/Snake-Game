package example.Controller;

import example.ImageUtil;
import example.Model.StartFrameMain;
import example.MusicPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;


public class EndingFrameController extends StartFrameController {
    @FXML
    private ImageView playAgainBtnImg, mainMenuBtnImg;

    @FXML
    private Label scoreLabel;

    public void setScoreLabel(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    @FXML
    public void onPlayAgain() {
        playAgainBtnImg.setImage(ImageUtil.images.get("play-again-btn"));
    }

    @FXML
    public void onPlayAgainHover() {
        playAgainBtnImg.setImage(ImageUtil.images.get("play-again-hover"));
    }

    @FXML
    public void onMainMenu() {
        mainMenuBtnImg.setImage(ImageUtil.images.get("main-menu-btn"));
    }

    @FXML
    public void onMainMenuHover() {
        mainMenuBtnImg.setImage(ImageUtil.images.get("main-menu-hover"));
    }
    @FXML
    public void playAgain() throws IOException, InterruptedException {
        scoreLabel.setVisible(false);
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
        setScoreLabel(MyFrameController.getFinalScore());
    }

    @Override
    public void backToMainMenu() throws IOException {
        scoreLabel.setVisible(false);
        super.backToMainMenu();
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/main-menu-music.mp3"));
    }
}
