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

    public boolean updateData(int id_produk, int jumlah_dibeli, int id_transaksi) {
        String sql = "UPDATE transactions SET id_produk = ? ,jumlah_dibeli = ? WHERE id_transaksi = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_produk);
            pstmt.setInt(2, jumlah_dibeli);
            pstmt.setInt(3, id_transaksi);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? "Transaksi berhasil diubah!" : "Gagal ubah transaksi.");
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
        String sql = "DELETE FROM transactions WHERE id_transaksi = ?";

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
                "JOIN categories c ON p.id_kategori = c.id_kategori ORDER BY id_transaksi DESC";

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

        // --- CORRECTED SQL QUERY ---
        // This query now selects the price, stock, and joins with the categories table
        // to get the category name.
        // NOTE: I have inferred your table names ('products', 'categories') from your
        // 'loadData' method.
        String sql = "SELECT p.id_produk, p.nama, p.harga, p.stok, c.nama_kategori " +
                "FROM products p " +
                "LEFT JOIN categories c ON p.id_kategori = c.id_kategori " +
                "ORDER BY p.nama ASC";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // --- EXTRACT ALL DATA FROM RESULTSET ---
                int id_produk = rs.getInt("id_produk");
                String nama = rs.getString("nama");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");
                String nama_kategori = rs.getString("nama_kategori");

                // --- USE THE FULL CONSTRUCTOR ---
                // Now we create the Product object with all the data we fetched.
                Product product = new Product(id_produk, nama, harga, stok, nama_kategori);

                productList.add(product);
            }

        } catch (SQLException e) {
            // It's good practice to print the stack trace during development to see the
            // full error.
            e.printStackTrace();
            PopUpAlert.popupErr("Database Error", "Failed to load products", "Error: " + e.getMessage());
        }

        return productList;
    }
}
