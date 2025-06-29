package com.example;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.model.Product;
import com.example.model.SessionData;
import com.example.utils.IResultableController;

public class App extends Application {
    public static SessionData userDatabse = new SessionData();
    private static Scene scene;

    public static Product userSelect = new Product(0, "nulll", 1, 1);

    @Override
    public void start(Stage stage) throws IOException {
        showSessionPopup(); // tampilkan popup dulu

        try {
            Image appIcon = new Image(getClass().getResourceAsStream("/com/example/Images/W.png"));
            scene = new Scene(loadFXML("Main"));
            stage.setScene(scene);
            stage.getIcons().add(appIcon);
            stage.setTitle("UAS-PBO-2025");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static boolean showSessionPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/session.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Create Session");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            Object controllerObj = loader.getController();
            if (controllerObj instanceof IResultableController resultController) {
                return resultController.isSuccess();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}