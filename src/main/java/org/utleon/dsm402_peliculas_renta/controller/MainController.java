package org.utleon.dsm402_peliculas_renta.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class MainController {

    @FXML
    private Button btnStart;

    @FXML
    private Hyperlink linkGitHub;

    public void initialize() {
        btnStart.setOnAction(e -> {
            try {
                loadLogin();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        linkGitHub.setOnAction(event -> {
            final String GITHUB = "https://github.com/DevOjeda016";
            try {
                URI uri = new URI(GITHUB);
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(uri); // Open Browser
                } else {
                    System.out.println("Browser aren't supported.");
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }

    public void loadLogin() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/utleon/dsm402_peliculas_renta/login-view.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Movies Center - Login");
        stage.setScene(scene);
        stage.show();

        //Close window
        stage = (Stage) btnStart.getScene().getWindow();
        stage.close();
    }
}
