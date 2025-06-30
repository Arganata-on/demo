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
    private Label labelCategory;

    @FXML
    private Label labelHarga;

    @FXML
    private TextField jumlahBarang;

    private boolean isSuccess = false;

    @FXML
    void cancelData(ActionEvent event) {
        Utils.closeWindow(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        produkChoice.setItems(db.getAllProduk());

        produkChoice.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {

                        labelHarga.setText(String.valueOf(newValue.getHarga()));
                        labelCategory.setText(newValue.getNama_kategori());
                    } else {

                        labelHarga.setText("0,00");
                        labelCategory.setText("Pilih Produk");
                    }
                });
    }

    @FXML
    void confirmData(ActionEvent event) {
        Product selectedProduct = produkChoice.getValue();
        try {
            int id_produk = selectedProduct.getId_produk();
            int jumlah_dibeli = Integer.parseInt(jumlahBarang.getText());

            if (jumlah_dibeli <= 0) {
                PopUpAlert.popupWarn("Input Error", "Jumlah Tidak Valid",
                        "Jumlah barang yang dibeli harus lebih dari 0.");
                return;
            }

            boolean dbSuccess = db.insertData(id_produk, jumlah_dibeli);
            if (dbSuccess) {
                System.out.println("Data berhasil disimpan!");
                this.isSuccess = true;
                Utils.closeWindow(event);
            }

        } catch (NumberFormatException e) {
            System.out.println("Input angka tidak valid ");
            PopUpAlert.popupWarn("Input Error", "Data tidak Valid",
                    "Pastikan input jumlah barang berupa angka.");
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
