package com.example.db;

import java.sql.*;

import com.example.model.Kategory;
import com.example.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.components.PopUpAlert;

public class ProductDatabase extends DatabaseConnection {

    public boolean insertData(int kodeProduk, String nama, int harga, int stok, int kategoryId) {
        String sql = "INSERT INTO products(id_produk, nama, harga, stok, id_kategori) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, kodeProduk);
            pstmt.setString(2, nama);
            pstmt.setInt(3, harga);
            pstmt.setInt(4, stok);
            pstmt.setInt(5, kategoryId);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? "Produk berhasil ditambahkan!" : "Gagal tambah produk.");
            return rowsInserted > 0;

        } catch (SQLIntegrityConstraintViolationException dupEx) {
            System.out.println("Produk dengan ID " + kodeProduk + " sudah ada!");
            PopUpAlert.popupWarn("Gagal Tambah", "Duplikat Produk", "ID produk sudah digunakan!");
            return false;
        } catch (Exception e) {
            PopUpAlert.popupErr("SQL Error", "Gagal Insert Data :" + nama, "SQL Error : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateData(int kodeProduk, String nama, int harga, int stok, int kategoriId) {
        String sql = "UPDATE products SET nama=?, harga=?, stok=?, id_kategori=? WHERE id_produk=?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nama);
            pstmt.setInt(2, harga);
            pstmt.setInt(3, stok);
            pstmt.setInt(4, kategoriId);
            pstmt.setInt(5, kodeProduk);

            int rowsUpdated = pstmt.executeUpdate();
            System.out.println(rowsUpdated > 0 ? "Produk berhasil diupdate!" : "Tidak ada produk yang diupdate.");
            return rowsUpdated > 0;

        } catch (Exception e) {
            PopUpAlert.popupErr("SQL Error", "Gagal Update Data : " + nama, "SQL Error : " + e.getMessage());
            return false;
        }
    }

    public boolean deleteData(int id) {
        String sql = "DELETE FROM products WHERE id_produk = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            System.out.println(rowsDeleted > 0 ? "Produk berhasil dihapus!" : "Tidak ada produk yang dihapus.");
            return rowsDeleted > 0;

        } catch (Exception e) {
            PopUpAlert.popupErr("SQL Error", "Gagal Menghapus Data", "Error : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<Product> loadData() {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String sql = "SELECT p.id_produk, p.nama, p.harga, p.stok, c.nama_kategori " +
                "FROM products p JOIN categories c ON p.id_kategori = c.id_kategori";

        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id_produk"),
                        rs.getString("nama"),
                        rs.getInt("harga"),
                        rs.getInt("stok"),
                        rs.getString("nama_kategori"));
                productList.add(product);
            }
        } catch (Exception e) {
            PopUpAlert.popupErr("Error Database", "Database Disconnect", "Database :" + e.getMessage());
        }

        return productList;
    }

    public ObservableList<Kategory> getAllkategory() {
        ObservableList<Kategory> kategoriesList = FXCollections.observableArrayList();
        String sql = "SELECT c.id_kategori, c.nama_kategori FROM categories c ORDER BY nama_kategori ASC";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Kategory kategory = new Kategory(rs.getInt("id_kategori"), rs.getString("nama_kategori"));
                kategoriesList.add(kategory);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kategoriesList;
    }
}
