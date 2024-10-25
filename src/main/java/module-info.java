module org.utleon.dsm402_peliculas_renta {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.utleon.dsm402_peliculas_renta to javafx.fxml;
    exports org.utleon.dsm402_peliculas_renta;
}