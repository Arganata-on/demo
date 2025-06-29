package com.example.db;

import java.sql.*;

import com.example.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.components.PopUpAlert;

public class ProductDatabase extends DatabaseConnection {

    public boolean insertData(int kodeProduk, String nama, int harga, int stok) {
        String sql = "INSERT INTO productsab(id_produk, nama, harga, stok) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, kodeProduk);
            pstmt.setString(2, nama);
            pstmt.setInt(3, harga);
            pstmt.setInt(4, stok);

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

    public boolean updateData(int kodeProduk, String nama, int harga, int stok) {
        String sql = "UPDATE productsab SET nama=?, harga=?, stok=? WHERE id_produk=?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nama);
            pstmt.setInt(2, harga);
            pstmt.setInt(3, stok);
            pstmt.setInt(4, kodeProduk);

            int rowsUpdated = pstmt.executeUpdate();
            System.out.println(rowsUpdated > 0 ? "Produk berhasil diupdate!" : "Tidak ada produk yang diupdate.");
            return rowsUpdated > 0;

        } catch (Exception e) {
            PopUpAlert.popupErr("SQL Error", "Gagal Update Data : " + nama, "SQL Error : " + e.getMessage());
            return false;
        }
    }

    public boolean deleteData(int id) {
        String sql = "DELETE FROM productsab WHERE id_produk = ?";

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
        String sql = "Select * from productsab";

        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id_produk"),
                        rs.getString("nama"),
                        rs.getInt("harga"),
                        rs.getInt("stok"));
                productList.add(product);
            }
        } catch (Exception e) {
            PopUpAlert.popupErr("Error Database", "Database Disconnect", "Database :" + e.getMessage());
        }

        return productList;
    }
}
