package com.example.restpos.ui;

import com.example.restpos.models.Order;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CheckoutScreen extends Stage {

    public CheckoutScreen(Order order, Stage owner) {
        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Checkout");

        VBox root = new VBox(20);
        root.setPadding(new Insets(50));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Checkout");
        titleLabel.getStyleClass().add("title");

        Label totalLabel = new Label("Total: " + order.getTotal());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Label paymentMethodLabel = new Label("Payment Method:");
        Button cashButton = new Button("Cash");
        Button bankTransferButton = new Button("Bank Transfer");

        grid.add(paymentMethodLabel, 0, 0);
        grid.add(cashButton, 1, 0);
        grid.add(bankTransferButton, 2, 0);

        // TODO: Add fields for payment details

        Button payButton = new Button("Pay");
        payButton.setOnAction(e -> {
            // TODO: Implement payment processing
            close();
        });

        root.getChildren().addAll(titleLabel, totalLabel, grid, payButton);
        setScene(new Scene(root));
    }
}