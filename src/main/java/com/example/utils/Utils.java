package com.example.utils;

import javafx.event.ActionEvent;

public class Utils {

    public static void closeWindow(ActionEvent event) {
        javafx.scene.Node source = (javafx.scene.Node) event.getSource();
        javafx.stage.Stage stage = (javafx.stage.Stage) source.getScene().getWindow();

        stage.close();
    }
}