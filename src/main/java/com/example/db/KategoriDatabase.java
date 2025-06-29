package com.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import com.example.components.PopUpAlert;
import com.example.model.Kategori;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KategoriDatabase extends DatabaseConnection {

    public boolean insertData(int idKategori, String namaKategori) {
        String sql = "INSERT INTO categories(id_kategori,nama_kategori) VALUES (?, ?)";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idKategori);
            pstmt.setString(2, namaKategori);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? "Produk berhasil ditambahkan!" : "Gagal tambah produk.");
            return rowsInserted > 0;

        } catch (SQLIntegrityConstraintViolationException dupEx) {
            System.out.println("Produk dengan ID " + idKategori + " sudah ada!");
            PopUpAlert.popupWarn("Gagal Tambah", "Duplikat Produk", "ID produk sudah digunakan!");
            return false;
        } catch (Exception e) {
            PopUpAlert.popupErr("SQL Error", "Gagal Insert Data :" + namaKategori, "SQL Error : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteData(int id) {
        String sql = "DELETE FROM categories WHERE id_kategori = ?";

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

    public boolean updateData(int idKategori, String namakategori) {
        String sql = "UPDATE categories SET nama_kategori = ? WHERE id_kategori = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, namakategori);

            pstmt.setInt(2, idKategori);

            int rowsUpdated = pstmt.executeUpdate();

            System.out.println(rowsUpdated > 0 ? "Kategori berhasil diupdate!"
                    : "Tidak ada kategori yang diupdate (ID tidak ditemukan).");
            return rowsUpdated > 0;

        } catch (Exception e) {

            PopUpAlert.popupErr("SQL Error", "Gagal Update Data Kategori: " + namakategori,
                    "SQL Error : " + e.getMessage());
            return false;
        }
    }

    public ObservableList<Kategori> loadData() {
        ObservableList<Kategori> categoryList = FXCollections.observableArrayList();
        String sql = "SELECT id_kategori,nama_kategori FROM categories";

        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {

                int idCategory = rs.getInt("id_kategori");
                String namaKategori = rs.getString("nama_kategori");

                Kategori category = new Kategori(
                        idCategory, namaKategori);

                categoryList.add(category);
            }
        } catch (Exception e) {
            PopUpAlert.popupErr("Error Database", "Database Disconnect", "Database Error: " + e.getMessage());
            e.printStackTrace();
        }

        return categoryList;
    }
}
