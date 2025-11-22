package com.example.restpos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        var label = new Label("Restaurant POS - Desktop (JavaFX)");
        var scene = new Scene(label, 1024, 700);
        primaryStage.setTitle("Restaurant POS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
