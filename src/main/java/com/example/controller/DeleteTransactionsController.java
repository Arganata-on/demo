package com.example.controller;

import com.example.App;

import com.example.db.*;
import com.example.utils.IResultableController;
import com.example.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DeleteTransactionsController implements IResultableController {

    TransactionsDatabase transactionsDatabase = new TransactionsDatabase();

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
        messegeDelete.setText("Yakin ingin menghapus transaksi: " + App.userSelectTransaction.getNama() + "?");
    }

    @FXML
    void handleHapusAction(ActionEvent event) {
        System.out.println("Tombol Delete diklik!");
        boolean dbSuccess = transactionsDatabase.deleteData(App.userSelectTransaction.getIdTransaksi());
        if (dbSuccess) {
            this.isSuccess = true;
        }
        App.userSelectTransaction.setIdTransaksi(0);
        Utils.closeWindow(event);
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

}
