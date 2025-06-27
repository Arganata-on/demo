package com.example;

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
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private void handleLoadButtonAction() {
        System.out.println("Tombol Load ditekan!");
    }

    @FXML
    private void handleInsertButtonAction() {
        System.out.println("Tombol Insert ditekan!");
    }

    @FXML
    private void handleUpdateButtonAction() {
        System.out.println("Tombol Update ditekan!");
    }

    @FXML
    private void handleDeleteButtonAction() {
        System.out.println("Tombol Delete ditekan!");
    }

    @FXML
    public void initialize() {
        
        System.out.println("Controller telah diinisialisasi.");
    }
}