package com.example.controller;

import com.example.components.PopUpAlert;
import com.example.db.KategoriDatabase;
import com.example.utils.IResultableController;
import com.example.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class insertPopUpKategoriController implements IResultableController {
    KategoriDatabase db = new KategoriDatabase();
    private boolean isSuccess = false;

    @FXML
    private TextField idKategori;

    @FXML
    private TextField namaKategori;

    @FXML
    void cancelData(ActionEvent event) {
        Utils.closeWindow(event);
    }

    @FXML
    void confirmData(ActionEvent event) {
        try {
            int id = Integer.parseInt(idKategori.getText());
            String nama = namaKategori.getText();

            boolean dbSuccess = db.insertData(id, nama);
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
