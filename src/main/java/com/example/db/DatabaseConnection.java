package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.App;

public class DatabaseConnection {
    protected Connection getConnection() {
        String url = "jdbc:mysql://localhost:" + App.userDatabse.get_port() + "/" + App.userDatabse.get_databaseName();
        String user = App.userDatabse.get_user();
        String password = App.userDatabse.get_password();

        try {
            System.out.println("Mencoba koneksi ke: " + url);
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("❌ Gagal koneksi ke database: " + e.getMessage());
            return null;
        }
    }
}
