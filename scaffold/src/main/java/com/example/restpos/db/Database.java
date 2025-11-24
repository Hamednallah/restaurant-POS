package com.example.restpos.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:restaurant.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTables() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            String schema = new String(Files.readAllBytes(Paths.get("migrations/V1__init.sql")));
            for (String s : schema.split(";")) {
                if (!s.trim().isEmpty()) {
                    stmt.execute(s);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}