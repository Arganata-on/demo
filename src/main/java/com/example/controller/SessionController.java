package com.example.controller;

import com.example.App;
import com.example.components.PopUpAlert;
import com.example.db.ProductDatabase;
import com.example.model.Product;
import com.example.utils.IResultableController;
import com.example.utils.Utils;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SessionController implements IResultableController {
    ProductDatabase db = new ProductDatabase();

    private boolean isSuccess = false;

    @FXML
    private TextField databaseName;

    @FXML
    private TextField password;

    @FXML
    private TextField port;

    @FXML
    private Button setSession;

    @FXML
    private TextField user;

    @FXML
    void confirmData(ActionEvent event) {
        App.userDatabse.set_databaseName(databaseName.getText());
        App.userDatabse.set_port(port.getText());
        App.userDatabse.set_user(user.getText());
        App.userDatabse.set_password(password.getText());

        try {
            ObservableList<Product> data = db.loadData();
            System.out.println(App.userDatabse.get_databaseName());
            if (data != null) {
                this.isSuccess = true;
                Utils.closeWindow(event);
            } else {
                PopUpAlert.popupErr("Error", "Session Out", "Coba Cek Database mu Kembali!");
            }
        } catch (Exception e) {
            PopUpAlert.popupErr("Error", "Connection false", e.getMessage());
        }
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

}
