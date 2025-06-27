package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class Controller {

    @FXML
    private TableView<?> tableViewData;

    @FXML
    private Button loadButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button tombolUpdate;

    @FXML
    private Button tombolDelete;

    @FXML
    private void handleLoadAction(ActionEvent event) {
        System.out.println("👉 Tombol Load diklik!");
        // Tambahkan logika untuk load data ke tableView
    }

    @FXML
    private void handleInsertAction(ActionEvent event) {
        System.out.println("➕ Tombol Insert diklik!");
        // Tambahkan logika untuk insert data
    }

    @FXML
    private void handleUpdateAction(ActionEvent event) {
        System.out.println("✏️ Tombol Update diklik!");
        // Tambahkan logika untuk update data
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        System.out.println("❌ Tombol Delete diklik!");
        // Tambahkan logika untuk hapus data
    }
}
