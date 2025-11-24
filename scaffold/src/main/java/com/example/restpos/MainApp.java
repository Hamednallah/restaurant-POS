package com.example.restpos;

import atlantafx.base.theme.PrimerLight;
import com.example.restpos.dao.UserDAO;
import com.example.restpos.db.Database;
import com.example.restpos.models.User;
import com.example.restpos.ui.LoginScreen;
import com.example.restpos.ui.ProductManagementScreen;
import com.example.restpos.ui.SidePanel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.example.restpos.models.User;

public class MainApp extends Application {

    private static final String CSS_PATH = "styles.css";
    private User currentUser;

    @Override
    public void start(Stage primaryStage) {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

        // Initialize the database and create a default user
        Database.createTables();
        if (UserDAO.getUserByUsername("admin") == null) {
            UserDAO.createUser("admin", "password", "Admin User", "admin");
        }

        // Create the login screen
        LoginScreen loginScreen = new LoginScreen(primaryStage, this::login);

        Scene scene = new Scene(loginScreen, 1024, 700);
        scene.getStylesheets().add(getClass().getResource(CSS_PATH).toExternalForm());
        primaryStage.setTitle("Restaurant POS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void login(User user, Stage stage) {
        this.currentUser = user;
        showMainApp(stage);
    }

    private void showMainApp(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        SidePanel sidePanel = new SidePanel(currentUser);
        root.setLeft(sidePanel);

        Label titleLabel = new Label("Restaurant POS");
        titleLabel.getStyleClass().add("title");

        // Add event handlers for the navigation buttons
        sidePanel.getChildren().forEach(node -> {
            if (node instanceof javafx.scene.control.Button) {
                javafx.scene.control.Button button = (javafx.scene.control.Button) node;
                button.setOnAction(e -> {
                    String text = button.getText();
                    if ("Product Management".equals(text)) {
                        root.setCenter(new ProductManagementScreen());
                    } else {
                        root.setCenter(new Label(text));
                    }
                });
            }
        });

        root.setCenter(titleLabel);
        primaryStage.getScene().setRoot(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}