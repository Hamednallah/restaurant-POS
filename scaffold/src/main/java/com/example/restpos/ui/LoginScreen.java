package com.example.restpos.ui;

import com.example.restpos.dao.UserDAO;
import com.example.restpos.models.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.function.BiConsumer;

public class LoginScreen extends BorderPane {

    public LoginScreen(Stage primaryStage, BiConsumer<User, Stage> onLoginSuccess) {
        getStyleClass().add("login-screen");

        // Left side
        VBox leftSide = new VBox(20);
        leftSide.setPadding(new Insets(50));
        leftSide.setAlignment(Pos.CENTER);
        leftSide.getStyleClass().add("login-left-side");
        Label logoLabel = new Label("Restaurant POS");
        logoLabel.getStyleClass().add("logo");
        Label developerLabel = new Label("Developed by Jules");
        developerLabel.getStyleClass().add("developer");
        leftSide.getChildren().addAll(logoLabel, developerLabel);
        setLeft(leftSide);

        // Right side
        VBox rightSide = new VBox(20);
        rightSide.setPadding(new Insets(50));
        rightSide.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("Login");
        titleLabel.getStyleClass().add("title");

        JFXTextField usernameField = new JFXTextField();
        usernameField.setPromptText("Username");
        usernameField.setLabelFloat(true);
        usernameField.setFocusColor(javafx.scene.paint.Color.web("#8B4513"));

        JFXPasswordField passwordField = new JFXPasswordField();
        passwordField.setPromptText("Password");
        passwordField.setLabelFloat(true);
        passwordField.setFocusColor(javafx.scene.paint.Color.web("#8B4513"));

        JFXButton loginButton = new JFXButton("Login");
        loginButton.getStyleClass().add("login-button");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (UserDAO.verifyPassword(username, password)) {
                User user = UserDAO.getUserByUsername(username);
                onLoginSuccess.accept(user, primaryStage);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password.");
                alert.showAndWait();
            }
        });

        rightSide.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton);
        setCenter(rightSide);
    }
}