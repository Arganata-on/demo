package com.example.controller;

import com.example.App;
import com.example.db.Database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class updatePopUpController {

    @FXML
    private TextField hargaBarang;

    @FXML
    private TextField namaBarang;

    @FXML
    private TextField stokBarang;

    private BerandaController mainController;

    public void setMainController(BerandaController controller) {
        this.mainController = controller;
    }

    @FXML
    void cencelData(ActionEvent event) {
        clearData();
        closeWindow(event);
    }

    public void refreshTabelBarang() {
        System.out.println("Tabel di-refresh dari InsertPopUp!");
    }

    @FXML
    void cofimData(ActionEvent event) {
        try {
            int harga = Integer.parseInt(hargaBarang.getText());
            int stok = Integer.parseInt(stokBarang.getText());
            String nama = namaBarang.getText();

            Database.updateData(App.userSelect.getId_produk(), nama, harga, stok);

            if (mainController != null) {
                mainController.refreshTabelBarang();
            }

            clearData();
            closeWindow(event);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Tidak Valid");
            alert.setHeaderText(null);
            alert.setContentText("Pastikan harga dan stok berisi angka yang valid.");
            alert.showAndWait();
        }
    }

    public void setData(String nama, String harga, String stok) {

        namaBarang.setText(nama);
        hargaBarang.setText(harga);
        stokBarang.setText(stok);
    }

    public void clearData() {
        namaBarang.setText("");
        hargaBarang.setText("");
        stokBarang.setText("");
    }

    void closeWindow(ActionEvent event) {
        javafx.scene.Node source = (javafx.scene.Node) event.getSource();
        javafx.stage.Stage stage = (javafx.stage.Stage) source.getScene().getWindow();

        stage.close();
    }
}
