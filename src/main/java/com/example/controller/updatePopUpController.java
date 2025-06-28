package com.example.controller;

import com.example.App;
import com.example.db.Database;
import com.example.utils.IResultableController;
import com.example.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class updatePopUpController implements IResultableController {

    @FXML
    private TextField hargaBarang;

    @FXML
    private TextField namaBarang;

    @FXML
    private TextField stokBarang;
    private boolean isSuccess = false;

    @FXML
    void cencelData(ActionEvent event) {
        clearData();
        Utils.closeWindow(event);
        ;
    }

    @FXML
    void cofimData(ActionEvent event) {
        try {
            int harga = Integer.parseInt(hargaBarang.getText());
            int stok = Integer.parseInt(stokBarang.getText());
            String nama = namaBarang.getText();

            boolean dbSuccess = Database.updateData(App.userSelect.getId_produk(), nama, harga, stok);
            if (dbSuccess) {
                this.isSuccess = true;
            }
            clearData();
            App.userSelect.setId_produk(0);
            Utils.closeWindow(event);

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

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

}
