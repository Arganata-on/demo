package com.example.controller;

import com.example.App;
import com.example.components.PopUpAlert;
import com.example.db.KategoriDatabase;
import com.example.utils.IResultableController;
import com.example.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class updatePopUpKategoriController implements IResultableController {

    KategoriDatabase db = new KategoriDatabase();
    private boolean isSuccess = false;

    @FXML
    private TextField idKategori;

    @FXML
    private TextField namaKategori;

    @FXML
    void cancelData(ActionEvent event) {
        clearData();
        Utils.closeWindow(event);
    }

    @FXML
    void confirmData(ActionEvent event) {
        try {

            String NamaKategori = namaKategori.getText();

            boolean dbSuccess = db.updateData(App.userSelectCategory.getIdKategori(), NamaKategori);
            if (dbSuccess) {
                this.isSuccess = true;
            }
            clearData();
            App.userSelectProduct.setId_produk(0);
            Utils.closeWindow(event);
        } catch (NumberFormatException e) {
            PopUpAlert.popupErr("Data validator", "Input Tidak Valid",
                    "Pastikan harga dan stok berisi angka yang valid.");
        }
    }

    public void setData(String nama) {
        namaKategori.setText(nama);
        ;
    }

    public void clearData() {
        namaKategori.setText("");
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

}
