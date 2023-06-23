module gui {
    requires javafx.controls;
    requires javafx.fxml;

    opens controllers.gui to javafx.fxml;
    opens model to javafx.base;
    exports controllers.gui;
    exports model;
}