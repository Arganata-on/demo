package com.example.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.example.db.Database;
import com.example.model.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;


public class Utils {

    public static ObservableList<Product> loadData() {
        System.out.println("Tombol Load diklik!");
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String sql = "SELECT p.id_produk, p.nama, p.harga, p.stok, c.nama_kategori " +
                "FROM products p JOIN categories c ON p.id_kategori = c.id_kategori";

        try (Connection conn = Database.getConnection();
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
            e.printStackTrace();
        }

        System.out.println("Jumlah produk ditemukan: " + productList.size());
        return productList;
    }

    public static void closeWindow(ActionEvent event) {
        javafx.scene.Node source = (javafx.scene.Node) event.getSource();
        javafx.stage.Stage stage = (javafx.stage.Stage) source.getScene().getWindow();

        stage.close();
    }
}