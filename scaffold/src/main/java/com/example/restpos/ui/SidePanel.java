package com.example.restpos.ui;

import com.example.restpos.models.User;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

public class SidePanel extends VBox {

    private boolean collapsed = false;
    private User currentUser;

    public SidePanel(User currentUser) {
        this.currentUser = currentUser;
        setPadding(new Insets(10));
        setSpacing(20);
        getStyleClass().add("side-panel");

        Button toggleButton = new Button();
        toggleButton.setGraphic(new FontIcon(FontAwesomeSolid.BARS));
        toggleButton.setOnAction(e -> toggle());

        getChildren().add(toggleButton);
        addNavigationButton("Dashboard", FontAwesomeSolid.HOME);
        addNavigationButton("POS", FontAwesomeSolid.DESKTOP);
        if (currentUser != null && "admin".equals(currentUser.getRole())) {
            addNavigationButton("Product Management", FontAwesomeSolid.BOX);
        }
        addNavigationButton("Settings", FontAwesomeSolid.COG);
    }

    private void addNavigationButton(String text, FontAwesomeSolid icon) {
        Button button = new Button(text);
        button.setGraphic(new FontIcon(icon));
        button.getStyleClass().add("navigation-button");
        getChildren().add(button);
    }

    private void toggle() {
        collapsed = !collapsed;
        for (javafx.scene.Node node : getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (collapsed) {
                    button.setContentDisplay(javafx.scene.control.ContentDisplay.GRAPHIC_ONLY);
                } else {
                    button.setContentDisplay(javafx.scene.control.ContentDisplay.LEFT);
                }
            }
        }
    }
}