package com.example.controller;

import com.example.App;

import com.example.db.Database;
import com.example.utils.IResultableController;
import com.example.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeletePopUpController implements IResultableController {

    @FXML
    private Button btnBatal;

    @FXML
    private Button btnHapus;

    private boolean isSuccess = false;

    @FXML
    void handleActionBatal(ActionEvent event) {
        Utils.closeWindow(event);
    }

    @FXML
    void handleHapusAction(ActionEvent event) {
        System.out.println("Tombol Delete diklik!");
        boolean dbSuccess = Database.deleteData(App.userSelect.getId_produk());
        if (dbSuccess) {
            this.isSuccess = true;
        }
        App.userSelect.setId_produk(0);
        Utils.closeWindow(event);
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

}
