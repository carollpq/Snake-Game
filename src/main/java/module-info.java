module com.snakegame.cw1setup {
    opens com.SnakeGame;
    opens com.SnakeGame.Controller;
    opens com.SnakeGame.Model.GameObjects.PowerUps;
    opens com.SnakeGame.Model.GameObjects;
    opens com.SnakeGame.Model.Utilities;

    requires java.desktop;
    requires jlayer;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.opencsv;
    requires org.junit.jupiter.api;
    requires org.junit.platform.launcher;
}