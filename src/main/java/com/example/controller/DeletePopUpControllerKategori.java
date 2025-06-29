package com.example.controller;

import com.example.App;

import com.example.db.*;
import com.example.utils.IResultableController;
import com.example.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DeletePopUpControllerKategori implements IResultableController {

    KategoriDatabase db = new KategoriDatabase();

    @FXML
    private Button btnBatal;

    @FXML
    private Label messegeDelete;

    @FXML
    private Button btnHapus;

    private boolean isSuccess = false;

    @FXML
    void handleActionBatal(ActionEvent event) {
        Utils.closeWindow(event);
    }

    @FXML
    public void initialize() {
        messegeDelete.setText("Yakin ingin menghapus produk: " + App.userSelectCategory.getKategoriNama() + "?");
    }

    @FXML
    void handleHapusAction(ActionEvent event) {
        System.out.println("Tombol Delete diklik!");
        boolean dbSuccess = db.deleteData(App.userSelectCategory.getIdKategori());
        if (dbSuccess) {
            this.isSuccess = true;
        }
        App.userSelectCategory.setIdKategori(0);
        Utils.closeWindow(event);
        db.loadData();
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

}
