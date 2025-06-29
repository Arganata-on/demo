package com.example.controller;

import java.io.IOException;

import com.example.App;
import com.example.components.PopUpAlert;
import com.example.db.KategoriDatabase;
import com.example.model.Kategori;
import com.example.utils.IResultableController;
import com.example.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class KategoriController {

    KategoriDatabase db = new KategoriDatabase();

    @FXML
    private Label alertLabel;

    @FXML
    private Button btnProduct;

    @FXML
    private Button deleteButton;

    @FXML
    private Button insertButton;

    @FXML
    private TableColumn<Kategori, Integer> kolomId;

    @FXML
    private TableColumn<Kategori, String> kolomNama;

    @FXML
    private Button loadButton;

    @FXML
    private TableView<Kategori> tableViewData;

    @FXML
    private Button updateButton;

    @FXML
    public void initialize() {
        kolomId.setCellValueFactory(new PropertyValueFactory<>("idKategori"));
        kolomNama.setCellValueFactory(new PropertyValueFactory<>("kategoriNama"));

        tableViewData.setItems(db.loadData());

        tableViewData.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        App.userSelectCategory.setIdKategori(newValue.getIdKategori());
                        ;
                        App.userSelectCategory.setKategoriNama(newValue.getKategoriNama());
                    }
                });
    }

    @FXML
    void handleDeleteAction(ActionEvent event) {
        if (App.userSelectCategory.getIdKategori() == 0) {
            PopUpAlert.popupWarn("Null Selected", "Peringatan", "Pilih item Trelebih Dahulu");
            return;
        }
        boolean success = showPopup(event, "/com/example/deletePopupKategori.fxml", "Delete Konfirmasi");
        if (success) {
            tableViewData.setItems(db.loadData());
            Utils.showFadingMessage(alertLabel, "Data berhasil dihapus.", 2, "#5DF57A");
        }
    }

    @FXML
    void handleInsertAction(ActionEvent event) {
        System.out.println("Tombol Insert diklik!");
        boolean success = showPopup(event, "/com/example/InsertPopUpKategori.fxml", "Insert Data");
        if (success) {
            Utils.showFadingMessage(alertLabel, "Data berhasil ditambahkan.", 2, "#5DF57A");
            tableViewData.setItems(db.loadData());
        }
    }

    @FXML
    void handleLoadAction(ActionEvent event) {
        tableViewData.setItems(db.loadData());
        if (!tableViewData.getItems().isEmpty()) {
            Utils.showFadingMessage(alertLabel, "Data has been loaded successfully.", 2, "#5DF57A");
        } else {
            Utils.showFadingMessage(alertLabel, "No Data Found.", 2, "#FF4560");
        }
    }

    @FXML
    void handleUpdateAction(ActionEvent event) {
        if (App.userSelectCategory.getIdKategori() == 0) {
            PopUpAlert.popupWarn("Null Selected", "Peringatan", "Pilih item Trelebih Dahulu");
            return;
        }
        boolean success = showPopup(event, "/com/example/updatePopUpKategori.fxml", "Update Data");
        if (success) {
            tableViewData.setItems(db.loadData());
            Utils.showFadingMessage(alertLabel, "Data berhasil diperbarui.", 2, "#5DF57A");
        }

    }

    @FXML
    void sceneProducts(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/Products.fxml"));

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void sceneTransactions(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/Main.fxml"));

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean showPopup(ActionEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Object controllerObj = loader.getController();

            if (controllerObj instanceof updatePopUpKategoriController updateController) {
                updateController.setData(String.valueOf(
                        App.userSelectCategory.getKategoriNama()));
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

}
