package com.example.restpos.ui;

import com.example.restpos.dao.UserDAO;
import com.example.restpos.models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.function.BiConsumer;

public class LoginScreen extends VBox {

    public LoginScreen(Stage primaryStage, BiConsumer<User, Stage> onLoginSuccess) {
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new Insets(50));
        getStyleClass().add("login-screen");

        Label titleLabel = new Label("Restaurant POS");
        titleLabel.getStyleClass().add("title");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        HBox usernameBox = new HBox(10, new FontIcon(FontAwesomeSolid.USER), usernameField);
        usernameBox.setAlignment(Pos.CENTER_LEFT);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        HBox passwordBox = new HBox(10, new FontIcon(FontAwesomeSolid.LOCK), passwordField);
        passwordBox.setAlignment(Pos.CENTER_LEFT);

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameBox, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordBox, 1, 1);

        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("login-button");
        loginButton.setGraphic(new FontIcon(FontAwesomeSolid.SIGN_IN_ALT));
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (UserDAO.verifyPassword(username, password)) {
                User user = UserDAO.getUserByUsername(username);
                onLoginSuccess.accept(user, primaryStage);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText("Invalid Credentials");
                alert.setContentText("Please check your username and password and try again.");
                alert.showAndWait();
            }
        });

        getChildren().addAll(titleLabel, grid, loginButton);
    }
}