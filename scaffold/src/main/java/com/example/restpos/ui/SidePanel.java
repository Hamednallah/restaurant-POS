package com.example.restpos.ui;

import com.example.restpos.models.User;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SidePanel extends VBox {

    private boolean collapsed = false;
    private User currentUser;
    private Map<Button, String> buttonTextMap = new HashMap<>();
    private List<Button> navigationButtons = new ArrayList<>();
    private Button selectedButton;

    public SidePanel(User currentUser) {
        this.currentUser = currentUser;
        setSpacing(20);
        getStyleClass().add("side-panel");

        Button toggleButton = new Button();
        toggleButton.setGraphic(new FontIcon(FontAwesomeSolid.BARS));
        toggleButton.setOnAction(e -> toggle());

        getChildren().add(toggleButton);
    }

    public void addNavigationButton(String text, FontAwesomeSolid icon, Runnable action) {
        Button button = new Button(text);
        button.setGraphic(new FontIcon(icon));
        button.getStyleClass().add("navigation-button");
        button.setOnAction(e -> {
            setSelectedButton(button);
            action.run();
        });
        buttonTextMap.put(button, text);
        navigationButtons.add(button);
        getChildren().add(button);
    }

    public List<Button> getNavigationButtons() {
        return navigationButtons;
    }

    public void setSelectedButton(Button button) {
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

        double targetWidth = collapsed ? 80 : 250;
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(prefWidthProperty(), targetWidth);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(200), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> {
            for (Map.Entry<Button, String> entry : buttonTextMap.entrySet()) {
                Button button = entry.getKey();
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