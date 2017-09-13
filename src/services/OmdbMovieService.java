package services;

import models.omdbmodels.OmdbMovie;
import services.httpclient.HttpClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static utils.Utils.URL_OMDB_SERVICE;

/**
 * Created by Miguel on 06/09/2017.
 */
public class OmdbMovieService {
    private String URL_OMDB_MOVIE_SERVICE = URL_OMDB_SERVICE + "&i=";
    private HttpClient httpClient = HttpClient.getHttpClientInstance();

    public OmdbMovieService() {

    }

    public OmdbMovie getOmdbMovie(String imdbId) {
        WebTarget target = httpClient.getClient().target(URL_OMDB_MOVIE_SERVICE + imdbId);
        Response response = target.request().get();
        return utils.Utils.jsonToOmdbMovie(response.readEntity(String.class));
    }
}
