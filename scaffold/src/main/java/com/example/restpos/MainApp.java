package com.example.restpos;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static final String CSS_PATH = "styles.css";

    @Override
    public void start(Stage primaryStage) {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Create a collapsible side panel
        VBox sidePanel = new VBox(10);
        sidePanel.setPadding(new Insets(10));
        sidePanel.getStyleClass().add("side-panel");

        Button toggleButton = new Button("Toggle Panel");
        toggleButton.setOnAction(e -> {
            if (root.getLeft() == null) {
                root.setLeft(sidePanel);
            } else {
                root.setLeft(null);
            }
        });

        Label titleLabel = new Label("Restaurant POS");
        titleLabel.getStyleClass().add("title");

        sidePanel.getChildren().addAll(
                new Label("Navigation"),
                new Button("Dashboard"),
                new Button("POS"),
                new Button("Settings")
        );

        root.setTop(toggleButton);
        root.setCenter(titleLabel);
        root.setLeft(sidePanel);

        Scene scene = new Scene(root, 1024, 700);
        scene.getStylesheets().add(getClass().getResource(CSS_PATH).toExternalForm());
        primaryStage.setTitle("Restaurant POS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}