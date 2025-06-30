package com.example.controller;

import java.net.URL;

import java.util.ResourceBundle;

import com.example.App;

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

public class UpdateTransactionsPopUpController implements IResultableController, Initializable {

    TransactionsDatabase db = new TransactionsDatabase();

    @FXML
    private ChoiceBox<Product> produkChoice;

    @FXML
    private Label hargaBarang;

    @FXML
    private Label lableCategory;

    @FXML
    private TextField jumlahBarang;

    private boolean isSuccess = false;

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        produkChoice.setItems(db.getAllProduk());

    }

    @FXML
    void cancelData(ActionEvent event) {
        clearData();
        Utils.closeWindow(event);

    }

    @FXML

    void confirmData(ActionEvent event) {
        Product selectedProduct = produkChoice.getValue();
        try {
            int id_produk = selectedProduct.getId_produk();
            int jumlah_dibeli = Integer.parseInt(jumlahBarang.getText());
            boolean dbSuccess = db.updateData(id_produk, jumlah_dibeli,
                    App.userSelectTransaction.getIdTransaksi());
            if (dbSuccess) {
                System.out.println("Data berhasil disimpan!");
                this.isSuccess = true;
                App.userSelectTransaction.setIdTransaksi(0);
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

    public void setData(String nama, String harga, String jumlah, String namaKategory) {
        selectProductByName(nama);
        hargaBarang.setText(harga);
        jumlahBarang.setText(jumlah);
        lableCategory.setText(namaKategory);
    }

    public void clearData() {
        hargaBarang.setText("");
        jumlahBarang.setText("");
        lableCategory.setText("");
        produkChoice.getSelectionModel().clearSelection();
    }

    public void selectProductByName(String namaProduct) {
        produkChoice.getItems().stream()
                .filter(product -> product.getNama().equals(namaProduct))
                .findFirst()
                .ifPresent(product -> produkChoice.setValue(product));
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;

    }

}
