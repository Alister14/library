module Biblioteka {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires xstream;

    exports pl.main to javafx.graphics, xstream;
    opens pl.controllers to javafx.fxml, xstream;
    opens pl.struckture to javafx.base, xstream;
    opens pl.Data to xstream, javafx.base;
}