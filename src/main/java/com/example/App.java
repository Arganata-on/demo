package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

import com.example.model.Product;

public class App extends Application {
    private static Scene scene;

    public static Product userSelect = new Product(0, "nulll", 1, 1);

    @Override
    public void start(Stage stage) throws IOException {
        Image appIcon = new Image(getClass().getResourceAsStream("/com/example/Images/W.png"));
        scene = new Scene(loadFXML("Main"));
        stage.setScene(scene);
        stage.getIcons().add(appIcon);
        stage.setTitle("UAS-PB0-2025");
        stage.show();
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
}