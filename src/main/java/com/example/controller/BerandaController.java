package com.example.controller;

import java.io.IOException;

import com.example.App;
import com.example.components.PopUpAlert;
import com.example.db.Database;
import com.example.model.Product;
import com.example.utils.Utils;

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

public class BerandaController {

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

        tableViewData.setItems(Utils.loadData());

        tableViewData.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        App.userSelect.setId_produk(newValue.getId_produk());
                        App.userSelect.setNama(newValue.getNama());
                        App.userSelect.setHarga(newValue.getHarga());
                        App.userSelect.setStok(newValue.getStok());
                        App.userSelect.setNama_kategori(newValue.getNama_kategori());
                    }
                });
    }

    @FXML
    private void handleLoadAction() {
        System.out.println("Tombol Load diklik!");
        tableViewData.setItems(Utils.loadData());
    }

    @FXML
    private void handleInsertAction(ActionEvent event) {
        System.out.println("Tombol Insert diklik!");
        showPopup(event, "/com/example/InsertPopUp.fxml", "insert data");
    }

    @FXML
    private void handleUpdateAction(ActionEvent event) {
        if (App.userSelect.getId_produk() == 0) {
            PopUpAlert.popupWarn("Null Selected", "Peringatan", "Pilih item Trelebih Dahulu");
            return;
        }
        showPopup(event, "/com/example/updatePopUp.fxml", "Update Data");
    }

    @FXML
    private void handleDeleteAction() {
        System.out.println("Tombol Delete diklik!");
        if (App.userSelect.getId_produk() == 0) {
            PopUpAlert.popupWarn("Null Selected", "Peringatan", "Pilih item Trelebih Dahulu");
            return;
        }

        Database.deleteData(App.userSelect.getId_produk());
        tableViewData.setItems(Utils.loadData());
    }

    private void showPopup(ActionEvent event, String fxmlFile, String title) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Object controller = loader.getController();

            if (controller instanceof InsertPopUpController insertController) {
                insertController.setMainController(this);
            }

            if (controller instanceof updatePopUpController updateController) {
                updateController.setMainController(this);
                updateController.setData(
                        App.userSelect.getNama(),
                        String.valueOf(App.userSelect.getHarga()),
                        String.valueOf(App.userSelect.getStok()));
            }

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            System.err.println(" Failed to load FXML file. ");
            e.printStackTrace();
        }
    }

    public void refreshTabelBarang() {
        System.out.println("resfres");
        tableViewData.setItems(Utils.loadData());
    }
}