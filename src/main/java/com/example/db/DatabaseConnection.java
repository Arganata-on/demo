package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    protected static final String URL = "jdbc:mysql://localhost:3306/toko_db";
    protected static final String USER = "root";
    protected static final String PASSWORD = "";

    protected Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println(" Gagal koneksi ke database: " + e.getMessage());
            return null;
        }
    }
}
