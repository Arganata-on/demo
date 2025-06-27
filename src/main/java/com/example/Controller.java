package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private TableView<Product> tableViewData;

    @FXML
    private TableColumn<Product, Integer> kolomId;

    @FXML
    private TableColumn<Product, String> kolomNama;

    @FXML
    private TableColumn<Product, Integer> kolomHarga;

    @FXML
    private TableColumn<Product, Integer> kolomStok;

    @FXML
    private TableColumn<Product, String> kolomKategori;

    @FXML
    private Button loadButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    public void initialize() {
        kolomId.setCellValueFactory(new PropertyValueFactory<>("id_produk"));
        kolomNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kolomHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        kolomStok.setCellValueFactory(new PropertyValueFactory<>("stok"));
        kolomKategori.setCellValueFactory(new PropertyValueFactory<>("nama_kategori"));
    }

    @FXML
    private void handleLoadAction() {
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
        tableViewData.setItems(productList);
    }

    @FXML
    private void handleInsertAction(ActionEvent event) {
        System.out.println("Tombol Insert diklik!");
        showPopup(event, "/com/example/InsertPopUp.fxml", "inert data");

    }

    @FXML
    private void handleUpdateAction() {
        System.out.println("Tombol Update diklik!");
    }

    @FXML
    private void handleDeleteAction() {
        System.out.println("Tombol Delete diklik!");
    }

    private void showPopup(ActionEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            popupStage.setTitle(title);
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (IOException e) {
            System.err.println("⚠️ Failed to load FXML file: " + fxmlFile);
            e.printStackTrace();
        }
    }
}