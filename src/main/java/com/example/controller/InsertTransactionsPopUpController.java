package com.example.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.components.PopUpAlert;
import com.example.db.*;
import com.example.utils.IResultableController;
import com.example.utils.Utils;
import com.example.model.Product;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InsertTransactionsPopUpController implements IResultableController, Initializable {

    TransactionsDatabase db = new TransactionsDatabase();

    @FXML
    private ChoiceBox<Product> produkChoice;

    @FXML
    private TextField hargaBarang;

    @FXML
    private TextField kodeBarang;

    @FXML
    private TextField namaBarang;

    @FXML
    private TextField stokBarang;

    @FXML
    private Label labelHarga;

    private boolean isSuccess = false;

    @FXML
    void cancelData(ActionEvent event) {
        Utils.closeWindow(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produkChoice.setItems(db.getAllProduk());
    }

    @FXML
    void confirmData(ActionEvent event) {
        Product selectedProduct = produkChoice.getValue();
        if (selectedProduct == null) {
            PopUpAlert.popupWarn("Input Error", "Kategori atau Produk Belum Dipilih",
                    "Anda harus memilih kategori dan produk untuk barang.");
            return;
        }
        try {
            int id_produk = selectedProduct.getId_produk();
            int jumlah_dibeli = Integer.parseInt(stokBarang.getText());

            boolean dbSuccess = db.insertData(id_produk, jumlah_dibeli);
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
