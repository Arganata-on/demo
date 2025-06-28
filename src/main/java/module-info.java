module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires transitive javafx.graphics;

    opens com.example.controller to javafx.fxml;
    opens com.example.model to javafx.base, javafx.fxml;
    // opens com.example.controller to javafx.fxml;

    exports com.example;
    exports com.example.model;

}
