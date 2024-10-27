module org.utleon.dsm402_peliculas_renta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.utleon.dsm402_peliculas_renta to javafx.fxml;
    exports org.utleon.dsm402_peliculas_renta;
    exports org.utleon.dsm402_peliculas_renta.controller;
    exports org.utleon.dsm402_peliculas_renta.model;
    opens org.utleon.dsm402_peliculas_renta.controller to javafx.fxml;
}