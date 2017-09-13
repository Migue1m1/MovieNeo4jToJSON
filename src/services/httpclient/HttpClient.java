package services.httpclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Created by Miguel on 07/09/2017.
 */
public class HttpClient {
    private Client client;
    private static HttpClient httpClient;

    public static  HttpClient getHttpClientInstance() {
        if (httpClient == null) {
            httpClient = new HttpClient();
        }
        return httpClient;
    }

    private HttpClient() {
        client = ClientBuilder.newClient();
    }

    public Client getClient() {
        return client;
    }
}
