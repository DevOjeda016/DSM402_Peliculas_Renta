package org.utleon.dsm402_peliculas_renta.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.utleon.dsm402_peliculas_renta.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldUser;

    @FXML
    private Hyperlink linkExit;

    public ArrayList<User> users = new ArrayList<>();;
    public String[][] userData = {
            {"DevOjeda016", "danielojeda16vvz@gmail.com", "DevOjeda2005*"},
            {"johndoe", "johndoe@example.com", "password123"},
            {"janedoe", "janedoe@example.com", "securepassword"},
            {"mikesmith", "mikesmith@example.com", "mypassword"},
            {"annjohnson", "annjohnson@example.com", "pass1234"},
            {"peterparker", "peterparker@example.com", "spideyPass"},
            {"brucewayne", "brucewayne@example.com", "batman123"},
            {"clarkkent", "clarkkent@example.com", "supermanPW"},
            {"tonystark", "tonystark@example.com", "ironman123"},
            {"steverogers", "steverogers@example.com", "capPass"},
    };


    public void initialize() {
        for (String[] data : userData) {
            User user = new User();
            user.setUsername(data[0]);
            user.setEmail(data[1]);
            user.setPassword(data[2]);
            users.add(user);
        }

        btnLogin.setOnAction(event -> {
            final String userOrEmail = fieldUser.getText();
            final String pass = fieldPassword.getText();
            validateCredentials(userOrEmail, pass);
        });

        linkExit.setOnAction(event -> {
            System.exit(0);
        });
    }

    public void validateCredentials(String userOrEmail, String pass) {
        if (userOrEmail.isEmpty() || pass.isEmpty()) {
            Alert alert = new Alert(
                    Alert.AlertType.ERROR,
                    "Necesita usuario y contraseña para iniciar sesión"
            );
            alert.showAndWait();
        } else {
            if (findUser(userOrEmail, pass) != null) {
                try {
                    loadHome();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Alert alert = new Alert(
                        Alert.AlertType.ERROR,
                        "Las credenciales son incorrectas. Intente nuevamente"
                );
                alert.showAndWait();
            }
        }
    }

    public User findUser(String userOrEmail, String pass) {
        for (User user : users){
            if ((user.getUsername().equals(userOrEmail) || user.getEmail().equals(userOrEmail)) && user.getPassword().equals(pass)) {
                return user;
            }
        };
        return null;
    }

    public void loadHome() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/utleon/dsm402_peliculas_renta/home-view.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Movies Center - Home");
        stage.setScene(scene);
        stage.show();

        //Close window
        stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
    }
}
