package org.utleon.dsm402_peliculas_renta.model;

import java.util.Objects;

public class Movie {
    private int id;
    private String title;
    private int releaseYear;
    private String genre;
    private String language;
    private String duration;
    private float rentalPrice;
    private boolean available;
    private String img;

    public Movie(int id, String title, int releaseYear, String genre, String language, String duration, float rentalPrice, boolean available, String img) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.language = language;
        this.duration = duration;
        this.rentalPrice = rentalPrice;
        this.available = available;
        this.img = img;
    }

    public Movie() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public float getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(float rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", duration='" + duration + '\'' +
                ", rentalPrice=" + rentalPrice +
                ", available=" + available +
                ", img='" + img + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return releaseYear == movie.releaseYear && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseYear);
    }
}
