package com.example.controller;

import java.io.IOError;
import java.io.IOException;

import com.example.App;
import com.example.components.PopUpAlert;
import com.example.db.ProductDatabase;

import com.example.utils.IResultableController;
import com.example.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class updatePopUpController implements IResultableController {

    ProductDatabase db = new ProductDatabase();
    private boolean isSuccess = false;

    @FXML
    private TextField hargaBarang;

    @FXML
    private TextField namaBarang;

    @FXML
    private TextField stokBarang;

    @FXML
    void cancelData(ActionEvent event) throws IOException {
        clearData();
        if (App.isRoute()) {
            App.setRoot("Main");
        }
        Utils.closeWindow(event);
    }

    @FXML
    public void initialize() {
        setData(App.userSelect.getNama(), String.valueOf(App.userSelect.getHarga()),
                String.valueOf(App.userSelect.getStok()));
    }

    @FXML
    void confirmData(ActionEvent event) throws IOException {

        try {
            int harga = Integer.parseInt(hargaBarang.getText());
            int stok = Integer.parseInt(stokBarang.getText());
            String nama = namaBarang.getText();

            boolean dbSuccess = db.updateData(App.userSelect.getId_produk(), nama, harga, stok);
            if (dbSuccess) {
                this.isSuccess = true;
            }
            clearData();
            App.userSelect.setId_produk(0);
            if (App.isRoute()) {
                App.setRoot("Main");
            }
            Utils.closeWindow(event);
        } catch (NumberFormatException e) {
            PopUpAlert.popupErr("Data validator", "Input Tidak Valid",
                    "Pastikan harga dan stok berisi angka yang valid.");
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
