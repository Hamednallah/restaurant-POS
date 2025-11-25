package com.example.restpos.ui;

import com.example.restpos.dao.ExpenseDAO;
import com.example.restpos.models.Expense;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ExpenseManagementScreen extends BorderPane {

    public ExpenseManagementScreen() {
        setPadding(new Insets(20));

        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        Label titleLabel = new Label("Expense Management");
        titleLabel.getStyleClass().add("title");

        TableView<Expense> expensesTable = new TableView<>();
        TableColumn<Expense, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Expense, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<Expense, Double> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        expensesTable.getColumns().addAll(titleCol, categoryCol, amountCol);
        expensesTable.setItems(FXCollections.observableArrayList(ExpenseDAO.getAllExpenses()));

        Button addButton = new Button("Add Expense");
        // TODO: Add expense dialog

        content.getChildren().addAll(titleLabel, expensesTable, addButton);
        setCenter(content);
    }
}