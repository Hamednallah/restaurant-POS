package com.example.restpos.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:restaurant.db";
    private static final String MIGRATIONS_PATH = "migrations";

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException ignored) {
        }
        return conn;
    }

    /**
     * Apply all migration files found under classpath `resources/migrations`.
     * Files are applied in lexical order and recorded in `schema_migrations`.
     */
    public static void migrate() {
        try {
            List<String> files = listResourceFiles(MIGRATIONS_PATH);
            Collections.sort(files);

            try (Connection conn = getConnection()) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("CREATE TABLE IF NOT EXISTS schema_migrations (version TEXT PRIMARY KEY, applied_at TEXT NOT NULL DEFAULT (datetime('now'))) ");
                }

                conn.setAutoCommit(false);

                for (String filename : files) {
                    if (isApplied(conn, filename)) {
                        continue;
                    }

                    String resourcePath = MIGRATIONS_PATH + "/" + filename;
                    String sql = readResourceAsString(resourcePath);
                    try (Statement stmt = conn.createStatement()) {
                        for (String s : sql.split(";")) {
                            if (!s.trim().isEmpty()) {
                                stmt.execute(s);
                            }
                        }

                        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO schema_migrations(version) VALUES (?)")) {
                            ps.setString(1, filename);
                            ps.executeUpdate();
                        }

                        conn.commit();
                    } catch (SQLException e) {
                        conn.rollback();
                        throw e;
                    }
                }
            }

        } catch (IOException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Backwards-compatible API. Older code calls `createTables()`; keep that working.
     */
    public static void createTables() {
        migrate();
    }

    private static boolean isApplied(Connection conn, String version) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM schema_migrations WHERE version = ?")) {
            ps.setString(1, version);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private static String readResourceAsString(String resourcePath) throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
        if (in == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        }
        return sb.toString();
    }

    /**
     * List file names under a resource folder. Supports running from filesystem and from JAR.
     */
    private static List<String> listResourceFiles(String path) throws IOException, URISyntaxException {
        List<String> filenames = new ArrayList<>();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL dirURL = cl.getResource(path);

        if (dirURL != null && dirURL.getProtocol().equals("file")) {
            File folder = new File(dirURL.toURI());
            File[] files = folder.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isFile()) filenames.add(f.getName());
                }
            }
        } else if (dirURL != null && dirURL.getProtocol().equals("jar")) {
            JarURLConnection jarConn = (JarURLConnection) dirURL.openConnection();
            try (JarFile jar = jarConn.getJarFile()) {
                Enumeration<JarEntry> entries = jar.entries();
                String prefix = path + "/";
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.startsWith(prefix) && !entry.isDirectory()) {
                        String entryName = name.substring(prefix.length());
                        if (!entryName.isEmpty()) filenames.add(entryName);
                    }
                }
            }
        } else {
            // Fallback: try to load via resource stream for a known filename list (best-effort)
            // If not found, return empty list so caller knows there's nothing to apply.
            URL url = cl.getResource(path);
            if (url != null) {
                // last resort - treat as file
                File folder = new File(url.toURI());
                File[] files = folder.listFiles();
                if (files != null) for (File f : files) if (f.isFile()) filenames.add(f.getName());
            }
        }

        return filenames;
    }
}