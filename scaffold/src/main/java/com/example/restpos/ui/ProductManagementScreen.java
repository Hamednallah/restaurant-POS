package com.example.restpos.ui;

import com.example.restpos.dao.CategoryDAO;
import com.example.restpos.dao.ProductDAO;
import com.example.restpos.models.Category;
import com.example.restpos.models.Product;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ProductManagementScreen extends BorderPane {

    public ProductManagementScreen() {
        setPadding(new Insets(20));

        JFXTabPane tabPane = new JFXTabPane();
        tabPane.setTabClosingPolicy(JFXTabPane.TabClosingPolicy.UNAVAILABLE);

        Tab productsTab = new Tab("Products");
        productsTab.setContent(createProductsTab());
        Tab categoriesTab = new Tab("Categories");
        categoriesTab.setContent(createCategoriesTab());

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

        JFXButton addButton = new JFXButton("Add Product");
        addButton.getStyleClass().add("button-raised");
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

        JFXButton addButton = new JFXButton("Add Category");
        addButton.getStyleClass().add("button-raised");
        // TODO: Add category dialog

        categoriesTabContent.getChildren().addAll(new Label("Categories"), categoriesTable, addButton);
        return categoriesTabContent;
    }
}