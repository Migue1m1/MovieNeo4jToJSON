package models;

import java.util.List;

/**
 * Created by Miguel on 06/09/2017.
 */
public class Movie {
    private String title;
    private String imdbId;
    private String released;
    private String rated;
    private String studio;
    private String description;
    private String genre;
    private String language;
    private String runtime;
    private String imageURL;
    private String imdbRating;
    private List<ActorRol> actors;
    private List<DirectorId> directors;

    public Movie(String title, String imdbId) {
        this.title = title;
        this.imdbId = imdbId;
        released = rated = studio = description = genre = language = runtime = imageURL = imdbRating = "";
    }

    public String getTitle() {
        return title;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getReleased() {
        return released;
    }

    public String getRated() {
        return rated;
    }

    public String getStudio() {
        return studio;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public List<ActorRol> getActors() {
        return actors;
    }

    public List<DirectorId> getDirectors() {
        return directors;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setActors(List<ActorRol> actors) {
        this.actors = actors;
    }

    public void setDirectors(List<DirectorId> directors) {
        this.directors = directors;
    }

    @Override
    public String toString(){
        return "Title: " + getTitle() + ", ImdbId: " + getImdbId() + ", ImdbRating: " + getImdbRating()
                + ", released: " + getReleased() + ", rated: " + getRated() + ", studio: " + getStudio()
                + ", description: " + getDescription() + ", genre: " + getGenre() + ", language: " + getLanguage()
                + ", runtime: " + getRuntime() + ", imageURL: " + getImageURL();
    }
}
