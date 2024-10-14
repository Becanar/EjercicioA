module org.example.ejercicioa {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicioa to javafx.fxml;
    exports org.example.ejercicioa;
}