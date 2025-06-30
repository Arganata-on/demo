package com.example.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;
import com.example.components.PopUpAlert;
import com.example.db.ProductDatabase;
import com.example.model.Kategori;
import com.example.utils.IResultableController;
import com.example.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class updatePopUpController implements IResultableController, Initializable {

    ProductDatabase db = new ProductDatabase();
    private boolean isSuccess = false;

    @FXML
    private ChoiceBox<Kategori> kategoryChoice;

    @FXML
    private TextField hargaBarang;

    @FXML
    private TextField namaBarang;

    @FXML
    private TextField stokBarang;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kategoryChoice.setItems(db.getAllkategory());
    }

    @FXML
    void cancelData(ActionEvent event) {
        clearData();
        Utils.closeWindow(event);
    }

    @FXML
    void confirmData(ActionEvent event) {
        Kategori selectedKategory = kategoryChoice.getValue();
        try {
            int harga = Integer.parseInt(hargaBarang.getText());
            int stok = Integer.parseInt(stokBarang.getText());
            String nama = namaBarang.getText();
            int kategoryId = selectedKategory.getIdKategori();

            boolean dbSuccess = db.updateData(App.userSelectProduct.getId_produk(), nama, harga, stok, kategoryId);
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

    public void setData(String nama, String harga, String stok, String namaKategory) {

        namaBarang.setText(nama);
        hargaBarang.setText(harga);
        stokBarang.setText(stok);
        selectKategoryByName(namaKategory);
    }

    public void clearData() {
        namaBarang.setText("");
        hargaBarang.setText("");
        stokBarang.setText("");
        kategoryChoice.getSelectionModel().clearSelection();
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    public void selectKategoryByName(String namaKategory) {

        kategoryChoice.getItems().stream()
                .filter(kategory -> kategory.getKategoriNama().equals(namaKategory))
                .findFirst()
                .ifPresent(kategory -> kategoryChoice.setValue(kategory));

    }

}
