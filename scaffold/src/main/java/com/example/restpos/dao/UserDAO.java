package com.example.restpos.dao;

import com.example.restpos.db.Database;
import com.example.restpos.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

    public static void createUser(String username, String password, String fullName, String role) {
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        String sql = "INSERT INTO users(username, password_hash, full_name, role) VALUES(?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            pstmt.setString(3, fullName);
            pstmt.setString(4, role);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error creating user", e);
        }
    }

    public static User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("full_name"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            log.error("Error getting user by username", e);
        }
        return user;
    }

    public static boolean verifyPassword(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null) {
            return BCrypt.checkpw(password, user.getPasswordHash());
        }
        return false;
    }
}