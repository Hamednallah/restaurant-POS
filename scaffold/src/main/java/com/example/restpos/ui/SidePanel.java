package com.example.restpos.ui;

import com.example.restpos.models.User;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.HashMap;
import java.util.Map;

public class SidePanel extends VBox {

    private boolean collapsed = false;
    private User currentUser;
    private Map<JFXButton, String> buttonTextMap = new HashMap<>();
    private JFXButton selectedButton;

    public SidePanel(User currentUser) {
        this.currentUser = currentUser;
        setSpacing(20);
        getStyleClass().add("side-panel");

        JFXButton toggleButton = new JFXButton();
        FontIcon icon = new FontIcon(FontAwesomeSolid.BARS);
        icon.setIconSize(24);
        icon.setIconColor(javafx.scene.paint.Color.web("#8B4513"));
        toggleButton.setGraphic(icon);
        toggleButton.setOnAction(e -> toggle());

        HBox toggleBox = new HBox(toggleButton);
        toggleBox.setAlignment(Pos.CENTER);
        getChildren().add(toggleBox);
    }

    public void addNavigationButton(String text, FontAwesomeSolid icon, Runnable action) {
        FontIcon fontIcon = new FontIcon(icon);
        fontIcon.setIconSize(24);
        JFXButton button = new JFXButton(text);
        button.setGraphic(fontIcon);
        button.getStyleClass().add("navigation-button");
        button.setOnAction(e -> {
            setSelectedButton(button);
            action.run();
        });
        buttonTextMap.put(button, text);
        getChildren().add(button);
    }

    public void setSelectedButton(JFXButton button) {
        if (selectedButton != null) {
            selectedButton.getStyleClass().remove("selected");
        }
        selectedButton = button;
        if (selectedButton != null) {
            selectedButton.getStyleClass().add("selected");
        }
    }

    private void toggle() {
        collapsed = !collapsed;

        double targetWidth = collapsed ? 100 : 250;
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(prefWidthProperty(), targetWidth);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(200), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> {
            for (Map.Entry<JFXButton, String> entry : buttonTextMap.entrySet()) {
                JFXButton button = entry.getKey();
                if (collapsed) {
                    button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    button.setText("");
                } else {
                    button.setContentDisplay(ContentDisplay.LEFT);
                    button.setText(entry.getValue());
                }
            }
        });
        timeline.play();
    }
}