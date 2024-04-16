module iesthiar {
    requires javafx.controls;
    requires javafx.fxml;

    opens iesthiar to javafx.fxml;
    exports iesthiar;
}
