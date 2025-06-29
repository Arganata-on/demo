package com.example.controller;

import java.io.IOException;

import com.example.App;
import com.example.components.PopUpAlert;
import com.example.db.ProductDatabase;
import com.example.model.Product;
import com.example.utils.IResultableController;
import com.example.utils.Utils;

import javafx.scene.control.Label;
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

public class ProductsController {

    ProductDatabase db = new ProductDatabase();

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
    private Label alertLabel;

    @FXML
    public void initialize() {
        kolomId.setCellValueFactory(new PropertyValueFactory<>("id_produk"));
        kolomNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kolomHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        kolomStok.setCellValueFactory(new PropertyValueFactory<>("stok"));
        kolomKategori.setCellValueFactory(new PropertyValueFactory<>("nama_kategori"));

        tableViewData.setItems(db.loadData());

        tableViewData.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        App.userSelectProduct.setId_produk(newValue.getId_produk());
                        App.userSelectProduct.setNama(newValue.getNama());
                        App.userSelectProduct.setHarga(newValue.getHarga());
                        App.userSelectProduct.setStok(newValue.getStok());
                        App.userSelectProduct.setNama_kategori(newValue.getNama_kategori());
                    }
                });
    }

    @FXML
    void sceneTransactions(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/Main.fxml"));

            Scene currentScene = ((Node) event.getSource()).getScene();
            App.userSelectProduct.setId_produk(0);
            currentScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void sceneCategory(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/Category.fxml"));

            Scene currentScene = ((Node) event.getSource()).getScene();
            App.userSelectProduct.setId_produk(0);
            currentScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoadAction() {
        tableViewData.setItems(db.loadData());
        if (!tableViewData.getItems().isEmpty()) {
            Utils.showFadingMessage(alertLabel, "Data has been loaded successfully.", 2, "#5DF57A");
        } else {
            Utils.showFadingMessage(alertLabel, "No Data Found.", 2, "#FF4560");
        }

    }

    @FXML
    private void handleInsertAction(ActionEvent event) {
        System.out.println("Tombol Insert diklik!");
        boolean success = showPopup(event, "/com/example/InsertPopUp.fxml", "Insert Data");
        if (success) {
            refreshTabelBarang();
            Utils.showFadingMessage(alertLabel, "Data berhasil ditambahkan.", 2, "#5DF57A");
        }
    }

    @FXML
    private void handleUpdateAction(ActionEvent event) {
        if (App.userSelectProduct.getId_produk() == 0) {
            PopUpAlert.popupWarn("Null Selected", "Peringatan", "Pilih item Trelebih Dahulu");
            return;
        }
        boolean success = showPopup(event, "/com/example/updatePopUp.fxml", "Update Data");
        if (success) {
            refreshTabelBarang();
            Utils.showFadingMessage(alertLabel, "Data berhasil diperbarui.", 2, "#5DF57A");
        }

    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        if (App.userSelectProduct.getId_produk() == 0) {
            PopUpAlert.popupWarn("Null Selected", "Peringatan", "Pilih item Trelebih Dahulu");
            return;
        }
        if (App.userSelectProduct.getId_produk() == 0) {
            PopUpAlert.popupWarn("Null Selected", "Peringatan", "Pilih item Trelebih Dahulu");
            return;
        }
        boolean success = showPopup(event, "/com/example/deletePopup.fxml", "Delete Konfirmasi");
        if (success) {
            refreshTabelBarang();
            Utils.showFadingMessage(alertLabel, "Data berhasil dihapus.", 2, "#5DF57A");
        }
    }

    private boolean showPopup(ActionEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Object controllerObj = loader.getController();

            if (controllerObj instanceof updatePopUpController updateController) {
                updateController.setData(App.userSelectProduct.getNama(),
                        String.valueOf(App.userSelectProduct.getHarga()),
                        String.valueOf(App.userSelectProduct.getStok()), App.userSelectProduct.getNama_kategori());
            }

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.setTitle(title);
            stage.setScene(new Scene(root));

            stage.showAndWait();

            if (controllerObj instanceof IResultableController resultController) {
                return resultController.isSuccess();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void refreshTabelBarang() {
        tableViewData.setItems(db.loadData());
    }

}