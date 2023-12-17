module CW1Setup {
    opens example;
    opens example.Controller;
    opens example.Model;

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