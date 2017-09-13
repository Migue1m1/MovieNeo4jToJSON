package models.omdbmodels;

import java.io.Serializable;

/**
 * Created by Miguel on 06/09/2017.
 */
public class OmdbMovie implements Serializable {
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Genre;
    private String Language;
    private String Country;
    private String Poster;
    private String imdbRating;

    public OmdbMovie() {
        Title = Year = Rated = Released = Genre = Language = Country = Poster = imdbRating = "";
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getGenre() {
        return Genre;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getPoster() {
        return Poster;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    @Override
    public String toString(){
        return "Title: " + getTitle() + ", Year: " + getYear() + ", Rated: " + getRated()
                + ", Imdb rating: " + getImdbRating() + ", released: " + getReleased()
                + ", Genre: " + getGenre() + ", Language: " + getLanguage()
                + ", Country: " + getCountry() + ", Poster: " + getPoster();
    }
}
