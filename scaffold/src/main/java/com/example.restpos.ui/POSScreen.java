package com.example.restpos.ui;

import com.example.restpos.dao.ProductDAO;
import com.example.restpos.models.Order;
import com.example.restpos.models.OrderItem;
import com.example.restpos.models.Product;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class POSScreen extends BorderPane {

    private Order currentOrder;
    private VBox orderSummary;
    private Label totalLabel;

    public POSScreen() {
        this.currentOrder = new Order();
        setPadding(new Insets(20));

        // Product Grid
        TilePane productGrid = new TilePane();
        productGrid.setPadding(new Insets(10));
        productGrid.setHgap(10);
        productGrid.setVgap(10);

        List<Product> products = ProductDAO.getAllProducts();
        for (Product product : products) {
            Button productButton = new Button(product.getNameEn());
            productButton.setPrefSize(120, 120);
            productButton.setOnAction(e -> addToOrder(product));
            productGrid.getChildren().add(productButton);
        }

        setCenter(productGrid);

        // Order Summary
        orderSummary = new VBox(10);
        orderSummary.setPadding(new Insets(10));
        orderSummary.setPrefWidth(300);
        orderSummary.getStyleClass().add("order-summary");

        Label titleLabel = new Label("Order Summary");
        titleLabel.getStyleClass().add("title");

        totalLabel = new Label("Total: 0.0");
        Button checkoutButton = new Button("Checkout");
        checkoutButton.setOnAction(e -> {
            CheckoutScreen checkoutScreen = new CheckoutScreen(currentOrder, (Stage) getScene().getWindow());
            checkoutScreen.show();
        });

        orderSummary.getChildren().addAll(titleLabel, new Label("Items:"), totalLabel, checkoutButton);
        setRight(orderSummary);
    }

    private void addToOrder(Product product) {
        currentOrder.addItem(product);
        updateOrderSummary();
    }

    private void updateOrderSummary() {
        orderSummary.getChildren().remove(2, orderSummary.getChildren().size() - 2);

        for (OrderItem item : currentOrder.getItems()) {
            Label itemLabel = new Label(item.getProduct().getNameEn() + " x" + item.getQuantity());
            orderSummary.getChildren().add(orderSummary.getChildren().size() - 2, itemLabel);
        }

        totalLabel.setText("Total: " + currentOrder.getTotal());
    }
}