package com.example.controller;

import com.example.components.PopUpAlert;
import com.example.db.Database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InsertPopUpController {

    @FXML
    private TextField hargaBarang;

    @FXML
    private TextField kodeBarang;

    @FXML
    private TextField namaBarang;

    @FXML
    private TextField stokBarang;

    private BerandaController mainController;

    public void setMainController(BerandaController controller) {
        this.mainController = controller;
    }

    @FXML
    void cencelData(ActionEvent event) {

        javafx.scene.Node source = (javafx.scene.Node) event.getSource();
        javafx.stage.Stage stage = (javafx.stage.Stage) source.getScene().getWindow();

        stage.close();
    }

    @FXML
    void cofimData(ActionEvent event) {
        try {
            int kode = Integer.parseInt(kodeBarang.getText());
            String nama = namaBarang.getText();
            int harga = Integer.parseInt(hargaBarang.getText());
            int stok = Integer.parseInt(stokBarang.getText());

            boolean success = Database.insertData(kode, nama, harga, stok);
            if (success) {
                System.out.println("Data berhasil disimpan!");

                if (mainController != null) {
                    mainController.refreshTabelBarang();
                }

                javafx.scene.Node source = (javafx.scene.Node) event.getSource();
                javafx.stage.Stage stage = (javafx.stage.Stage) source.getScene().getWindow();
                stage.close();
            }

        } catch (NumberFormatException e) {
            System.out.println("Input angka tidak valid ");
            PopUpAlert.popupWarn("Input Error", "Data tidak Valid",
                    "Pastikan input harga, stok, dan kode berupa angka");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menyimpan data ");
            e.printStackTrace();
            PopUpAlert.popupWarn("Gagal Simpan", "Gagal Simpan", "Terjadi kesalahan tidak terduga");
        }
    }

}
