package com.example.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.components.PopUpAlert;
import com.example.db.*;
import com.example.utils.IResultableController;
import com.example.utils.Utils;
import com.example.model.Kategory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class InsertPopUpController implements IResultableController, Initializable {

    ProductDatabase db = new ProductDatabase();

    @FXML
    private ChoiceBox<Kategory> kategoryChoice;

    @FXML
    private TextField hargaBarang;

    @FXML
    private TextField kodeBarang;

    @FXML
    private TextField namaBarang;

    @FXML
    private TextField stokBarang;

    private boolean isSuccess = false;

    @FXML
    void cancelData(ActionEvent event) {
        Utils.closeWindow(event);
    }

    /**
     * ADDED: This method is called automatically to setup the controller.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populate the choicebox from the database
        kategoryChoice.setItems(db.getAllkategory());
    }

    @FXML
    void confirmData(ActionEvent event) {
        Kategory selectedKategory = kategoryChoice.getValue();
        if (selectedKategory == null) {
            PopUpAlert.popupWarn("Input Error", "Kategori Belum Dipilih",
                    "Anda harus memilih kategori untuk barang.");
            return;
        }
        try {
            int kode = Integer.parseInt(kodeBarang.getText());
            String nama = namaBarang.getText();
            int harga = Integer.parseInt(hargaBarang.getText());
            int stok = Integer.parseInt(stokBarang.getText());
            int kategoryId = selectedKategory.getIdKategory();

            boolean dbSuccess = db.insertData(kode, nama, harga, stok, kategoryId);
            if (dbSuccess) {
                System.out.println("Data berhasil disimpan!");
                this.isSuccess = true;
                Utils.closeWindow(event);
            }

        } catch (NumberFormatException e) {
            System.out.println("Input angka tidak valid ");
            PopUpAlert.popupWarn("Input Error", "Data tidak Valid",
                    "Pastikan input harga, stok, dan kode berupa angka");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menyimpan data : " + e.getMessage());
            PopUpAlert.popupWarn("Gagal Simpan", "Gagal Simpan",
                    "Terjadi kesalahan tidak terduga :" + e.getLocalizedMessage());
        }
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

}
