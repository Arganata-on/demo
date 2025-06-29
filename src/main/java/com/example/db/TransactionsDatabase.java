package com.example.db;

import java.sql.*;

import com.example.model.Product;
import com.example.model.Transactions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.components.PopUpAlert;

public class TransactionsDatabase extends DatabaseConnection {

    public boolean insertData(int id_produk, int jumlah_dibeli) {
        String sql = "INSERT INTO transactions(id_produk, jumlah_dibeli) VALUES (?, ?)";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_produk);
            pstmt.setInt(2, jumlah_dibeli);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? "Transaksi berhasil ditambahkan!" : "Gagal tambah transaksi.");
            return rowsInserted > 0;

            // } catch (SQLIntegrityConstraintViolationException dupEx) {
            // System.out.println("Transaksi dengan ID " + id_transaksi + " sudah ada!");
            // PopUpAlert.popupWarn("Gagal Tambah", "Duplikat Transaksi", "ID transaksi
            // sudah digunakan!");
            // return false;
        } catch (Exception e) {
            PopUpAlert.popupErr("SQL Error", "Gagal Insert Data :" + id_produk, "SQL Error : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteData(int id) {
        String sql = "DELETE FROM transactions WHERE id_transaction = ?";

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

    public ObservableList<Transactions> loadData() {
        ObservableList<Transactions> transactionsList = FXCollections.observableArrayList();
        String sql = "SELECT t.id_transaksi, p.nama, p.harga, t.jumlah_dibeli, c.nama_kategori " +
                "FROM transactions t " +
                "JOIN products p ON t.id_produk = p.id_produk " +
                "JOIN categories c ON p.id_kategori = c.id_kategori";

        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {

                int idTransaksi = rs.getInt("id_transaksi");
                String namaProduk = rs.getString("nama");
                int hargaProduk = rs.getInt("harga");
                int jumlahDibeli = rs.getInt("jumlah_dibeli");
                String namaKategori = rs.getString("nama_kategori");

                Transactions transactions = new Transactions(
                        idTransaksi,
                        namaProduk,
                        namaKategori,
                        hargaProduk,
                        jumlahDibeli);

                transactionsList.add(transactions);
            }
        } catch (Exception e) {
            PopUpAlert.popupErr("Error Database", "Database Disconnect", "Database Error: " + e.getMessage());
            e.printStackTrace();
        }

        return transactionsList;
    }

    public ObservableList<Product> getAllProduk() {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String sql = "SELECT p.id_produk, p.nama FROM products p ORDER BY p.nama ASC;";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id_produk"),
                        rs.getString("nama"));
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
}
