module iesthiar {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens iesthiar.persona to javafx.fxml;
    exports iesthiar.persona;

    opens iesthiar to javafx.fxml;
    exports iesthiar;
}
