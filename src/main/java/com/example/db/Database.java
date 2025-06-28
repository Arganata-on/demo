package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.example.components.PopUpAlert;

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

    public static boolean insertData(int kodeProduk, String nama, int harga, int stok) {
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
                System.out.println(" Produk berhasil ditambahkan!");
            }
            return rowsInserted > 0;

        } catch (SQLIntegrityConstraintViolationException dupEx) {
            System.out.println(" Produk dengan ID " + kodeProduk + " sudah ada di database!");
            PopUpAlert.popupWarn("Gagal Tambah", "Duplikat Produk", "ID produk sudah digunakan!");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gagal menambahkan produk ");
            return false;
        }
    }

    public static void updateData(int kodeProduk, String nama, int harga, int stok) {
        String sql = "UPDATE products SET nama=?, harga=?, stok=?, id_kategori=? WHERE id_produk=?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nama);
            pstmt.setInt(2, harga);
            pstmt.setInt(3, stok);
            pstmt.setInt(4, 1);
            pstmt.setInt(5, kodeProduk);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Produk berhasil diupdate!");
            } else {
                System.out.println("Tidak ada produk yang terupdate.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gagal update produk.");
        }
    }

    public static void deleteData(int id) {
        String sql = "DELETE FROM products WHERE id_produk = ?;";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Produk berhasil dihapus!");
            } else {
                System.out.println("Tidak ada produk yang dihapus.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gagal hapus produk.");
        }
    }

}
