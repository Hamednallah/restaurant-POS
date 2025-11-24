package com.example.restpos.dao;

import com.example.restpos.db.Database;
import com.example.restpos.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final Logger log = LoggerFactory.getLogger(ProductDAO.class);

    public static void createProduct(String code, String nameAr, String nameEn, int categoryId, double price, String printerGroup, boolean active) {
        String sql = "INSERT INTO products(code, name_ar, name_en, category_id, price, printer_group, active) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, code);
            pstmt.setString(2, nameAr);
            pstmt.setString(3, nameEn);
            pstmt.setInt(4, categoryId);
            pstmt.setDouble(5, price);
            pstmt.setString(6, printerGroup);
            pstmt.setBoolean(7, active);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error creating product", e);
        }
    }

    public static List<Product> getAllProducts() {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("name_ar"),
                        rs.getString("name_en"),
                        rs.getInt("category_id"),
                        rs.getDouble("price"),
                        rs.getString("printer_group"),
                        rs.getBoolean("active")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            log.error("Error getting all products", e);
        }
        return products;
    }

    public static void updateProduct(int id, String code, String nameAr, String nameEn, int categoryId, double price, String printerGroup, boolean active) {
        String sql = "UPDATE products SET code = ?, name_ar = ?, name_en = ?, category_id = ?, price = ?, printer_group = ?, active = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, code);
            pstmt.setString(2, nameAr);
            pstmt.setString(3, nameEn);
            pstmt.setInt(4, categoryId);
            pstmt.setDouble(5, price);
            pstmt.setString(6, printerGroup);
            pstmt.setBoolean(7, active);
            pstmt.setInt(8, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error updating product", e);
        }
    }

    public static void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error deleting product", e);
        }
    }
}