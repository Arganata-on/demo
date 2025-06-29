package com.example.controller;

import com.example.components.PopUpAlert;
import com.example.db.*;
import com.example.utils.IResultableController;
import com.example.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InsertPopUpController implements IResultableController {

    ProductDatabase db = new ProductDatabase();

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
    void cencelData(ActionEvent event) {
        Utils.closeWindow(event);
    }

    @FXML
    void cofimData(ActionEvent event) {
        try {
            int kode = Integer.parseInt(kodeBarang.getText());
            String nama = namaBarang.getText();
            int harga = Integer.parseInt(hargaBarang.getText());
            int stok = Integer.parseInt(stokBarang.getText());

            boolean dbSuccess = db.insertData(kode, nama, harga, stok);
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
