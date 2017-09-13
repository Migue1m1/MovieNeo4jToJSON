package services;

import models.tmdbmodels.TmdbPerson;
import services.httpclient.HttpClient;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static utils.Utils.TMDB_RESPONSE_LANGUAGE;
import static utils.Utils.URL_TMDB_SERVICE;
import static utils.Utils.TMDB_API_KEY;

/**
 * Created by Miguel on 07/09/2017.
 */
public class TmdbActorService {
    private String URL_TMDB_ACTOR_SERVICE = URL_TMDB_SERVICE + "person/";
    private HttpClient httpClient = HttpClient.getHttpClientInstance();

    public TmdbPerson getTmdbActor(String id) {
        WebTarget target = httpClient.getClient().target(URL_TMDB_ACTOR_SERVICE
                + id + "?" + TMDB_API_KEY + "&" + TMDB_RESPONSE_LANGUAGE);

        Response response = target.request().get();
        return utils.Utils.jsonToTmdbActor(response.readEntity(String.class));
    }
}
