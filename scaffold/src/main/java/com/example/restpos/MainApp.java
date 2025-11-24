package com.example.restpos;

import atlantafx.base.theme.PrimerLight;
import com.example.restpos.dao.UserDAO;
import com.example.restpos.db.Database;
import com.example.restpos.models.User;
import com.example.restpos.ui.LoginScreen;
import com.example.restpos.ui.POSScreen;
import com.example.restpos.ui.ProductManagementScreen;
import com.example.restpos.ui.SidePanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

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

        SidePanel sidePanel = new SidePanel(currentUser);
        root.setLeft(sidePanel);

        Label titleLabel = new Label("Restaurant POS");
        titleLabel.getStyleClass().add("title");

        sidePanel.addNavigationButton("Dashboard", FontAwesomeSolid.HOME, () -> root.setCenter(new Label("Dashboard")));
        sidePanel.addNavigationButton("POS", FontAwesomeSolid.DESKTOP, () -> root.setCenter(new POSScreen()));
        if (currentUser != null && "admin".equals(currentUser.getRole())) {
            sidePanel.addNavigationButton("Product Management", FontAwesomeSolid.BOX, () -> root.setCenter(new ProductManagementScreen()));
        }
        sidePanel.addNavigationButton("Settings", FontAwesomeSolid.COG, () -> root.setCenter(new Label("Settings")));

        root.setCenter(titleLabel);
        primaryStage.getScene().setRoot(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}