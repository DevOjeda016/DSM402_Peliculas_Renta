package org.utleon.dsm402_peliculas_renta.controller;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.utleon.dsm402_peliculas_renta.model.Movie;

import java.util.Objects;

public class HomeController {
    @FXML
    private VBox buttons;

    @FXML
    private TableColumn<Movie, String> colDuration;

    @FXML
    private TableColumn<Movie, String> colDurationRent;

    @FXML
    private TableColumn<Movie, String> colGenre;

    @FXML
    private TableColumn<Movie, String> colGenreRent;

    @FXML
    private TableColumn<Movie, Integer> colId;

    @FXML
    private TableColumn<Movie, Integer> colIdRent;

    @FXML
    private TableColumn<Movie, String> colLanguage;

    @FXML
    private TableColumn<Movie, String> colLanguageRent;

    @FXML
    private TableColumn<Movie, Float> colPrice;

    @FXML
    private TableColumn<Movie, Float> colPriceRent;

    @FXML
    private TableColumn<Movie, String> colTitle;

    @FXML
    private TableColumn<Movie, String> colTitleRent;

    @FXML
    private TableColumn<Movie, Integer> colYear;

    @FXML
    private TableColumn<Movie, Integer> colYearRent;

    @FXML
    private TextField fieldDuration;

    @FXML
    private TextField fieldGenre;

    @FXML
    private TextField fieldLanguage;

    @FXML
    private TextField fieldPrice;

    @FXML
    private TextField fieldTitle;

    @FXML
    private TextField fieldYear;

    @FXML
    private ImageView imageView;

    @FXML
    private TableView<Movie> tableAvaible;

    @FXML
    private TableView<Movie> tableRent;

    @FXML
    private VBox vBoxForm;

    private String nameImage = "nothing.jpg";

    Button btnChooseFile = new Button("Escoger imagen");
    Button btnAdd = new Button("Añadir");
    Button btnDelete = new Button("Eliminar");
    Button btnEdit = new Button("Editar");
    Button btnConfirm = new Button("Confirmar cambios");
    Button btnRent = new Button("Rentar");
    Button btnReturn = new Button("Devolver");
    Button btnCancel = new Button("Cancelar");
    Button btnNewMovie = new Button("Nueva película");

    ObservableList<Movie> moviesAvialable;
    ObservableList<Movie> rentedMovies;
    int count = 20;

    public void initialize() {
        // Tabla de películas disponibles
        colId.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getId()));
        colTitle.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getTitle()));
        colGenre.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getGenre()));
        colYear.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getReleaseYear()));
        colDuration.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getDuration()));
        colLanguage.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getLanguage()));
        colPrice.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getRentalPrice()));
        moviesAvialable = FXCollections.observableArrayList();
        tableAvaible.setItems(moviesAvialable);

        // Tabla de películas rentadas
        colIdRent.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getId()));
        colTitleRent.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getTitle()));
        colGenreRent.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getGenre()));
        colYearRent.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getReleaseYear()));
        colDurationRent.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getDuration()));
        colLanguageRent.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getLanguage()));
        colPriceRent.setCellValueFactory(col -> new SimpleObjectProperty<>(col.getValue().getRentalPrice()));
        rentedMovies = FXCollections.observableArrayList();
        tableRent.setItems(rentedMovies);

        // Agregar datos iniciales
        populateMovies();

        // Estilo de botones
        styleButton(btnReturn);
        styleButton(btnAdd);
        styleButton(btnConfirm);
        styleButton(btnEdit);
        styleButton(btnDelete);
        styleButton(btnChooseFile);
        styleButton(btnRent);
        styleButton(btnCancel);
        styleButton(btnNewMovie);

        // Acciones
        buttons.getChildren().add(btnAdd);
        vBoxForm.getChildren().add(btnChooseFile);
        btnAdd.setOnAction(event -> add());
        btnChooseFile.setOnAction(event -> openFileChooser());
        btnNewMovie.setOnAction(event -> newMovie());
        btnEdit.setOnAction(event -> edit());
        tableAvaible.setOnMouseClicked(event -> {
            vBoxForm.getChildren().remove(btnChooseFile);
            buttons.getChildren().clear();
            buttons.getChildren().add(btnEdit);
            buttons.getChildren().add(btnRent);
            buttons.getChildren().add(btnNewMovie);
            fillField(tableAvaible);
            btnRent.setOnAction(event1 -> rent());
        });
        tableRent.setOnMouseClicked(event -> {
            vBoxForm.getChildren().remove(btnChooseFile);
            buttons.getChildren().clear();
            buttons.getChildren().add(btnReturn);
            buttons.getChildren().add(btnNewMovie);
            fillField(tableRent);
            btnReturn.setOnAction(event1 -> returnMovie());
        });
        imageView.setImage(new Image("file:C:\\Dev\\university\\javafx\\DSM402_Peliculas_Renta\\src\\main\\resources\\org\\utleon\\dsm402_peliculas_renta\\img\\" + nameImage));
    }

    private void populateMovies() {
        //add data for tables
        moviesAvialable.add(new Movie(1, "Inception", 2010, "Sci-Fi", "English", "148 min", 3.99f, true, "inception.jpg"));
        moviesAvialable.add(new Movie(2, "Parasite", 2019, "Thriller", "Korean", "132 min", 4.49f, true, "parasite.jpg"));
        moviesAvialable.add(new Movie(3, "The Dark Knight", 2008, "Action", "English", "152 min", 3.99f, true, "dark_knight.jpg"));
        moviesAvialable.add(new Movie(4, "Interstellar", 2014, "Sci-Fi", "English", "169 min", 4.99f, true, "interstellar.jpg"));
        moviesAvialable.add(new Movie(5, "The Godfather", 1972, "Crime", "English", "175 min", 2.99f, true, "godfather.jpg"));
        moviesAvialable.add(new Movie(6, "Spirited Away", 2001, "Animation", "Japanese", "125 min", 3.49f, true, "spirited_away.jpg"));
        moviesAvialable.add(new Movie(7, "Pulp Fiction", 1994, "Crime", "English", "154 min", 2.99f, true, "pulp_fiction.jpg"));
        moviesAvialable.add(new Movie(8, "The Matrix", 1999, "Sci-Fi", "English", "136 min", 3.49f, true, "matrix.jpg"));
        moviesAvialable.add(new Movie(9, "The Lion King", 1994, "Animation", "English", "88 min", 2.49f, true, "lion_king.jpg"));
        moviesAvialable.add(new Movie(10, "Gladiator", 2000, "Action", "English", "155 min", 3.99f, true, "gladiator.jpg"));

        rentedMovies.add(new Movie(11, "Titanic", 1997, "Romance", "English", "195 min", 3.99f, false, "titanic.jpg"));
        rentedMovies.add(new Movie(12, "Avatar", 2009, "Sci-Fi", "English", "162 min", 4.99f, false, "avatar.jpg"));
        rentedMovies.add(new Movie(13, "Forrest Gump", 1994, "Drama", "English", "142 min", 3.49f, false, "forrest_gump.jpg"));
        rentedMovies.add(new Movie(14, "Jurassic Park", 1993, "Adventure", "English", "127 min", 3.99f, false, "jurassic_park.jpg"));
        rentedMovies.add(new Movie(15, "The Shawshank Redemption", 1994, "Drama", "English", "142 min", 2.99f, false, "shawshank_redemption.jpg"));
        rentedMovies.add(new Movie(16, "Star Wars: A New Hope", 1977, "Sci-Fi", "English", "121 min", 3.49f, false, "star_wars.jpg"));
        rentedMovies.add(new Movie(17, "The Avengers", 2012, "Action", "English", "143 min", 3.99f, false, "avengers.jpg"));
        rentedMovies.add(new Movie(18, "Harry Potter and the Sorcerer's Stone", 2001, "Fantasy", "English", "152 min", 3.99f, false, "harry_potter.jpg"));
        rentedMovies.add(new Movie(19, "The Lord of the Rings: The Fellowship of the Ring", 2001, "Fantasy", "English", "178 min", 4.49f, false, "lord_of_the_rings.jpg"));
        rentedMovies.add(new Movie(20, "Back to the Future", 1985, "Sci-Fi", "English", "116 min", 3.49f, false, "back_to_the_future.jpg"));

    }

    public void rent() {
        Movie selectedMovie = tableAvaible.getSelectionModel().getSelectedItem();
        if (selectedMovie != null && selectedMovie.isAvailable()) {
            selectedMovie.setAvailable(false);
            moviesAvialable.remove(selectedMovie);
            rentedMovies.add(selectedMovie);
        }
        newMovie();
    }

    public void cleanForm() {
        fieldTitle.clear();
        fieldLanguage.clear();
        fieldPrice.clear();
        fieldDuration.clear();
        fieldYear.clear();
        fieldGenre.clear();
        nameImage = "nothing.jpg";
        imageView.setImage(new Image("file:C:\\Dev\\university\\javafx\\DSM402_Peliculas_Renta\\src\\main\\resources\\org\\utleon\\dsm402_peliculas_renta\\img\\" + nameImage));
    }

    public void returnMovie() {
        Movie selectedMovie = tableRent.getSelectionModel().getSelectedItem();
        if (selectedMovie != null && !selectedMovie.isAvailable()) {
            selectedMovie.setAvailable(true);
            rentedMovies.remove(selectedMovie);
            moviesAvialable.add(selectedMovie);
        }
        newMovie();
    }

    public void newMovie() {
        vBoxForm.getChildren().remove(btnChooseFile);
        buttons.getChildren().clear();
        vBoxForm.getChildren().add(btnChooseFile);
        buttons.getChildren().add(btnAdd);
        cleanForm();
    }


    public void clearEdit() {
        vBoxForm.getChildren().remove(btnChooseFile);
        buttons.getChildren().clear();
        buttons.getChildren().add(btnEdit);
        buttons.getChildren().add(btnRent);
        buttons.getChildren().add(btnNewMovie);
        imageView.setImage(new Image("file:C:\\Dev\\university\\javafx\\DSM402_Peliculas_Renta\\src\\main\\resources\\org\\utleon\\dsm402_peliculas_renta\\img\\" + nameImage));
    }

    public void edit() {
        vBoxForm.getChildren().add(btnChooseFile);
        buttons.getChildren().clear();
        buttons.getChildren().add(btnConfirm);
        buttons.getChildren().add(btnDelete);
        buttons.getChildren().add(btnCancel);
        btnDelete.setOnAction(event -> deleteMovie());
        btnCancel.setOnAction(event -> newMovie());
        btnChooseFile.setOnAction(event -> openFileChooser());
        btnConfirm.setOnAction(event -> confirmEdition());
    }

    public void deleteMovie() {
        Movie selectedMovie = tableAvaible.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            moviesAvialable.remove(selectedMovie);
            newMovie();
        }
        imageView.setImage(new Image("file:C:\\Dev\\university\\javafx\\DSM402_Peliculas_Renta\\src\\main\\resources\\org\\utleon\\dsm402_peliculas_renta\\img\\" + nameImage));
    }

    public void confirmEdition() {
        Movie selectedMovie = tableAvaible.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            selectedMovie.setTitle(fieldTitle.getText());
            selectedMovie.setGenre(fieldGenre.getText());
            selectedMovie.setDuration(fieldDuration.getText());
            selectedMovie.setLanguage(fieldLanguage.getText());
            selectedMovie.setReleaseYear(Integer.parseInt(fieldYear.getText()));
            selectedMovie.setRentalPrice(Float.parseFloat(fieldPrice.getText()));
            if (!Objects.equals(nameImage, "nothing.jpg")) {
                selectedMovie.setImg(nameImage);
            }
        }
        nameImage = "nothing.jpg";
        tableAvaible.refresh();
        clearEdit();

    }

    public void add() {
        Movie movie = new Movie(count++, fieldTitle.getText(), Integer.parseInt(fieldYear.getText()), fieldGenre.getText(),
                fieldLanguage.getText(), fieldDuration.getText(), Float.parseFloat(fieldPrice.getText()), true, nameImage);
        moviesAvialable.add(movie);
        cleanForm();
        imageView.setImage(new Image("file:C:\\Dev\\university\\javafx\\DSM402_Peliculas_Renta\\src\\main\\resources\\org\\utleon\\dsm402_peliculas_renta\\img\\" + nameImage));

    }

    public void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            nameImage = file.getName();
            try {
                Files.copy(file.toPath(), Path.of("C:\\Dev\\university\\javafx\\DSM402_Peliculas_Renta\\src\\main\\resources\\org\\utleon\\dsm402_peliculas_renta\\img\\" + nameImage), StandardCopyOption.REPLACE_EXISTING);
                imageView.setImage(new Image(file.toURI().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillField(TableView<Movie> tableView) {
        Movie selectedMovie = tableView.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            fieldTitle.setText(selectedMovie.getTitle());
            fieldGenre.setText(selectedMovie.getGenre());
            fieldDuration.setText(selectedMovie.getDuration());
            fieldLanguage.setText(selectedMovie.getLanguage());
            fieldYear.setText(String.valueOf(selectedMovie.getReleaseYear()));
            fieldPrice.setText(String.valueOf(selectedMovie.getRentalPrice()));
            imageView.setImage(new Image("file:C:\\Dev\\university\\javafx\\DSM402_Peliculas_Renta\\src\\main\\resources\\org\\utleon\\dsm402_peliculas_renta\\img\\" + selectedMovie.getImg()));
        }
    }

    private void styleButton(Button button) {
        button.getStyleClass().addAll("btn-secondary", "style-text-main");
        button.setPrefWidth(260.8);
        button.setPrefHeight(30);
        button.setMaxHeight(Double.MAX_VALUE);
    }
}
