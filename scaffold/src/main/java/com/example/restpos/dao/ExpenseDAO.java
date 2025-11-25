package com.example.restpos.dao;

import com.example.restpos.db.Database;
import com.example.restpos.models.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    private static final Logger log = LoggerFactory.getLogger(ExpenseDAO.class);

    public static void createExpense(Expense expense) {
        String sql = "INSERT INTO expenses(title, category, amount, tax, payment_method, reference, attachment_path, due_date, paid_date, is_recurring, recurrence_interval, created_by) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, expense.getTitle());
            pstmt.setString(2, expense.getCategory());
            pstmt.setDouble(3, expense.getAmount());
            pstmt.setDouble(4, expense.getTax());
            pstmt.setString(5, expense.getPaymentMethod());
            pstmt.setString(6, expense.getReference());
            pstmt.setString(7, expense.getAttachmentPath());
            pstmt.setString(8, expense.getDueDate());
            pstmt.setString(9, expense.getPaidDate());
            pstmt.setBoolean(10, expense.isRecurring());
            pstmt.setString(11, expense.getRecurrenceInterval());
            pstmt.setInt(12, expense.getCreatedBy());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error creating expense", e);
        }
    }

    public static List<Expense> getAllExpenses() {
        String sql = "SELECT * FROM expenses";
        List<Expense> expenses = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getDouble("tax"),
                        rs.getString("payment_method"),
                        rs.getString("reference"),
                        rs.getString("attachment_path"),
                        rs.getString("due_date"),
                        rs.getString("paid_date"),
                        rs.getBoolean("is_recurring"),
                        rs.getString("recurrence_interval"),
                        rs.getInt("created_by")
                );
                expenses.add(expense);
            }
        } catch (SQLException e) {
            log.error("Error getting all expenses", e);
        }
        return expenses;
    }

    public static void updateExpense(Expense expense) {
        String sql = "UPDATE expenses SET title = ?, category = ?, amount = ?, tax = ?, payment_method = ?, reference = ?, attachment_path = ?, due_date = ?, paid_date = ?, is_recurring = ?, recurrence_interval = ?, created_by = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, expense.getTitle());
            pstmt.setString(2, expense.getCategory());
            pstmt.setDouble(3, expense.getAmount());
            pstmt.setDouble(4, expense.getTax());
            pstmt.setString(5, expense.getPaymentMethod());
            pstmt.setString(6, expense.getReference());
            pstmt.setString(7, expense.getAttachmentPath());
            pstmt.setString(8, expense.getDueDate());
            pstmt.setString(9, expense.getPaidDate());
            pstmt.setBoolean(10, expense.isRecurring());
            pstmt.setString(11, expense.getRecurrenceInterval());
            pstmt.setInt(12, expense.getCreatedBy());
            pstmt.setInt(13, expense.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error updating expense", e);
        }
    }

    public static void deleteExpense(int id) {
        String sql = "DELETE FROM expenses WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error deleting expense", e);
        }
    }
}