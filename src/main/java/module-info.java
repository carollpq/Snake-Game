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
}