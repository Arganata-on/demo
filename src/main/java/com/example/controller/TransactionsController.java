package com.example.controller;

import java.io.IOException;

import com.example.App;
import com.example.components.PopUpAlert;
import com.example.db.TransactionsDatabase;
import com.example.model.Transactions;
import com.example.utils.IResultableController;

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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class TransactionsController {

    TransactionsDatabase db = new TransactionsDatabase();

    @FXML
    private TableView<Transactions> tableViewData;

    @FXML
    private TableColumn<Transactions, Integer> kolomId;

    @FXML
    private TableColumn<Transactions, String> kolomNama;

    @FXML
    private TableColumn<Transactions, String> kolomKategori;

    @FXML
    private TableColumn<Transactions, Integer> kolomHarga;

    @FXML
    private TableColumn<Transactions, Integer> kolomStok;

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
        kolomId.setCellValueFactory(new PropertyValueFactory<>("id_transaksi"));
        kolomNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kolomKategori.setCellValueFactory(new PropertyValueFactory<>("nama_kategori"));
        kolomHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        kolomStok.setCellValueFactory(new PropertyValueFactory<>("jumlah_dibeli"));

        tableViewData.setItems(db.loadData());

        tableViewData.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        App.userSelect.setId_produk(newValue.getId_transaksi());
                        App.userSelect.setNama(newValue.getNama());
                        App.userSelect.setNama_kategori(newValue.getNama_kategori());
                        App.userSelect.setHarga(newValue.getHarga());
                        App.userSelect.setStok(newValue.getJumlah_dibeli());
                    }
                });
    }

    @FXML
    void sceneProducts(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/Main.fxml"));

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleLoadAction() {
        App.userSelect.setId_produk(0);
        tableViewData.setItems(db.loadData());
        if (!tableViewData.getItems().isEmpty()) {
            showFadingMessage(alertLabel, "Data has been loaded successfully.", 2, "#5DF57A");
        } else {
            showFadingMessage(alertLabel, "No Data Found.", 2, "#FF4560");
        }

    }

    @FXML
    private void handleInsertAction(ActionEvent event) {
        System.out.println("Tombol Insert diklik!");
        boolean success = showPopup(event, "/com/example/InsertTransactionsPopUp.fxml", "Insert Data");
        if (success) {
            refreshTabelBarang();
            showFadingMessage(alertLabel, "Data berhasil ditambahkan.", 2, "#5DF57A");
        }
    }

    @FXML
    private void handleUpdateAction(ActionEvent event) {
        if (App.userSelect.getId_produk() == 0) {
            PopUpAlert.popupWarn("Null Selected", "Peringatan", "Pilih item Trelebih Dahulu");
            return;
        }
        boolean success = showPopup(event, "/com/example/updatePopUp.fxml", "Update Data");
        if (success) {
            refreshTabelBarang();
            showFadingMessage(alertLabel, "Data berhasil diperbarui.", 2, "#5DF57A");
        }

    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        if (App.userSelect.getId_produk() == 0) {
            PopUpAlert.popupWarn("Null Selected", "Peringatan", "Pilih item Trelebih Dahulu");
            return;
        }
        if (App.userSelect.getId_produk() == 0) {
            PopUpAlert.popupWarn("Null Selected", "Peringatan", "Pilih item Trelebih Dahulu");
            return;
        }
        boolean success = showPopup(event, "/com/example/deletePopup.fxml", "Delete Konfirmasi");
        if (success) {
            refreshTabelBarang();
            showFadingMessage(alertLabel, "Data berhasil dihapus.", 2, "#5DF57A");
        }
    }

    private boolean showPopup(ActionEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Object controllerObj = loader.getController();

            if (controllerObj instanceof updatePopUpController updateController) {
                updateController.setData(App.userSelect.getNama(), String.valueOf(App.userSelect.getHarga()),
                        String.valueOf(App.userSelect.getStok()), App.userSelect.getNama_kategori());
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

    public void showFadingMessage(Label label, String message, int seconds, String color) {
        label.setText(message);
        label.setTextFill(Color.web(color));

        label.setOpacity(1.0);

        PauseTransition delay = new PauseTransition(Duration.seconds(seconds));
        delay.setOnFinished(event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), label);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            fadeOut.setOnFinished(e -> label.setText(""));

            fadeOut.play();
        });

        delay.play();
    }

}