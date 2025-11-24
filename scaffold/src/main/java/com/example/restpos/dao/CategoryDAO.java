package com.example.restpos.dao;

import com.example.restpos.db.Database;
import com.example.restpos.models.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private static final Logger log = LoggerFactory.getLogger(CategoryDAO.class);

    public static void createCategory(String name) {
        String sql = "INSERT INTO categories(name) VALUES(?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error creating category", e);
        }
    }

    public static List<Category> getAllCategories() {
        String sql = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            log.error("Error getting all categories", e);
        }
        return categories;
    }

    public static void updateCategory(int id, String name) {
        String sql = "UPDATE categories SET name = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error updating category", e);
        }
    }

    public static void deleteCategory(int id) {
        String sql = "DELETE FROM categories WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error deleting category", e);
        }
    }
}