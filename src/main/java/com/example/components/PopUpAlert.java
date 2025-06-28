package com.example.components;

import javafx.scene.control.Alert;

public class PopUpAlert {
    public static void popupInfo(String title, String subTitle, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(subTitle);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void popupWarn(String title, String subTitle, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(subTitle);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void popupErr(String title, String subTitle, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(subTitle);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void popupConfirm(String title, String subTitle, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(subTitle);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
