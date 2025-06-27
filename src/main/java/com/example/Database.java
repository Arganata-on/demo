package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3307/arganataon";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.err.println("Koneksi database gagal! Error: " + e.getMessage());
            return null;
        }

    }

    public static void insertData(int kodeProduk, String nama, int harga, int stok) {
        String sql = "INSERT INTO products(id_produk, nama, harga, stok, id_kategori) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, kodeProduk);
            pstmt.setString(2, nama);
            pstmt.setInt(3, harga);
            pstmt.setInt(4, stok);
            pstmt.setInt(5, 1);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Produk berhasil ditambahkan!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan produk ");
        }
    }

}
