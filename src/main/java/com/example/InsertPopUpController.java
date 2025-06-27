package com.example;

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

            System.out.println("kode: " + kode);
            System.out.println("nama: " + nama);
            System.out.println("harga: " + harga);
            System.out.println("stok: " + stok);

            Database.insertData(kode, nama, harga, stok);
            System.out.println("Data berhasil disimpan! ");

        } catch (NumberFormatException e) {
            System.out.println("Input angka tidak valid ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menyimpan data ");
            e.printStackTrace();
        } finally {
            javafx.scene.Node source = (javafx.scene.Node) event.getSource();
            javafx.stage.Stage stage = (javafx.stage.Stage) source.getScene().getWindow();

            stage.close();
        }
    }

}
