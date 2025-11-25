package com.example.restpos.db;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
            InputStream inputStream = Database.class.getResourceAsStream("/migrations/V1__init.sql");
            if (inputStream == null) {
                throw new RuntimeException("Cannot find migrations/V1__init.sql in classpath");
            }
            String schema = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            for (String s : schema.split(";")) {
                if (!s.trim().isEmpty()) {
                    stmt.execute(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}