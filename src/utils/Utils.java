package utils;

import com.google.gson.Gson;
import models.omdbmodels.OmdbMovie;
import models.tmdbmodels.TmdbPerson;

import java.nio.file.Paths;

/**
 * Created by Miguel on 06/09/2017.
 */
public class Utils {
    public static String URL_OMDB_SERVICE = "http://www.omdbapi.com/?apikey=d52e522e";
    public static String URL_NEO4J = "bolt://localhost:7687";

    public static String URL_TMDB_SERVICE = "https://api.themoviedb.org/3/";
    public static String TMDB_API_KEY = "api_key=4e7a6f52820509df4af10c4397844070";
    public static String TMDB_RESPONSE_LANGUAGE = "language=en-US";

    public static String URL_TMDB_IMAGE = "http://image.tmdb.org/t/p/original/";

    public static String USER_NEO4J = "neo4j";
    public static String PASS_NEO4J = "1234";

    public static String JSON_ACTORS_FILE = Paths.get("").toAbsolutePath().toString()
                                            + "/files/actors.json";

    public static String JSON_DIRECTORS_FILE = Paths.get("").toAbsolutePath().toString()
            + "/files/directors.json";

    public static String JSON_MOVIES_FILE = Paths.get("").toAbsolutePath().toString()
            + "/files/movies.json";

    public static OmdbMovie jsonToOmdbMovie(String str) {
        Gson gsonObject = new Gson();
        return gsonObject.fromJson(str, OmdbMovie.class);
    }

    public static TmdbPerson jsonToTmdbActor(String str) {
        Gson gsonObject = new Gson();
        return gsonObject.fromJson(str, TmdbPerson.class);
    }

    public static String genderToString(int i) {
        switch (i) {
            case 1:
                return "Female";
            case 2:
                return "Male";
            default:
                return "Not Specified";
        }
    }

    public static String fixBiography(String biography){
        if (biography != null){
            return biography.replaceAll("\n|\\s{2,}", "");
        }
        else return "";
    }
}
