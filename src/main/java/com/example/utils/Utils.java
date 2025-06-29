package com.example.utils;



import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Utils {

    public static void closeWindow(ActionEvent event) {
        javafx.scene.Node source = (javafx.scene.Node) event.getSource();
        javafx.stage.Stage stage = (javafx.stage.Stage) source.getScene().getWindow();

        stage.close();
    }

     public static void showFadingMessage(Label label, String message, int seconds, String color) {
        label.setText(message);
        label.setTextFill(Color.web(color));

        label.setOpacity(1.0);

        PauseTransition delay = new PauseTransition(Duration.seconds(seconds));
        delay.setOnFinished(event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), label);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            fadeOut.setOnFinished(e -> label.setText(""));

            fadeOut.play();
        });

        delay.play();
    }
}
