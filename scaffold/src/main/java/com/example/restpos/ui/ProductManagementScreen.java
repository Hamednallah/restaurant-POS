package com.example.restpos.ui;

import com.example.restpos.dao.CategoryDAO;
import com.example.restpos.dao.ProductDAO;
import com.example.restpos.models.Category;
import com.example.restpos.models.Product;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductManagementScreen extends BorderPane {

    public ProductManagementScreen() {
        setPadding(new Insets(20));

        TabPane tabPane = new TabPane();
        Tab productsTab = new Tab("Products", createProductsTab());
        Tab categoriesTab = new Tab("Categories", createCategoriesTab());

        tabPane.getTabs().addAll(productsTab, categoriesTab);
        setCenter(tabPane);
    }

    private VBox createProductsTab() {
        VBox productsTabContent = new VBox(10);
        productsTabContent.setPadding(new Insets(10));

        TableView<Product> productsTable = new TableView<>();
        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nameEn"));
        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTable.getColumns().addAll(nameCol, priceCol);
        productsTable.setItems(FXCollections.observableArrayList(ProductDAO.getAllProducts()));

        Button addButton = new Button("Add Product");
        // TODO: Add product dialog

        productsTabContent.getChildren().addAll(new Label("Products"), productsTable, addButton);
        return productsTabContent;
    }

    private VBox createCategoriesTab() {
        VBox categoriesTabContent = new VBox(10);
        categoriesTabContent.setPadding(new Insets(10));

        TableView<Category> categoriesTable = new TableView<>();
        TableColumn<Category, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoriesTable.getColumns().add(nameCol);
        categoriesTable.setItems(FXCollections.observableArrayList(CategoryDAO.getAllCategories()));

        Button addButton = new Button("Add Category");
        // TODO: Add category dialog

        categoriesTabContent.getChildren().addAll(new Label("Categories"), categoriesTable, addButton);
        return categoriesTabContent;
    }
}