package com.example.restpos.ui;

import com.example.restpos.dao.UserDAO;
import com.example.restpos.models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.BiConsumer;

public class LoginScreen extends VBox {

    public LoginScreen(Stage primaryStage, BiConsumer<User, Stage> onLoginSuccess) {
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new Insets(50));

        Label titleLabel = new Label("Login");
        titleLabel.getStyleClass().add("title");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (UserDAO.verifyPassword(username, password)) {
                User user = UserDAO.getUserByUsername(username);
                onLoginSuccess.accept(user, primaryStage);
            } else {
                // Show error message
                System.out.println("Invalid username or password");
            }
        });

        getChildren().addAll(titleLabel, grid, loginButton);
    }
}