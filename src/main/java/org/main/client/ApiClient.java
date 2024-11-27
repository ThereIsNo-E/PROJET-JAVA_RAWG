package org.main.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.main.utils.UrlBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApiClient {
    private static ApiClient instance;
    private final OkHttpClient client;
    private final String apiKey;

    private ApiClient() {
        this.client = new OkHttpClient();
        this.apiKey = System.getProperty("API_KEY");
    }

    // Méthode assurant le fonctionnement "Singleton" de la classe
    public static ApiClient getInstance() {
        if (instance != null) {
            return instance;
        }
        // Sécurité contre de multiples threads
        synchronized (ApiClient.class) {
            if(instance == null) {
                instance = new ApiClient();
            }
        }
        return instance;
    }



    // Méthode pour exécuter une requête GET avec la clé API
    public String get(List<Map.Entry<String,String>> parameters, String segment) throws IOException {
        UrlBuilder urlBuilder = new UrlBuilder("https://api.rawg.io/api");
        buildUrl(parameters, segment, urlBuilder);
        Request request = urlBuilder.build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body() != null ? response.body().string() : null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void buildUrl(List<Map.Entry<String,String>> parameters, String segment, UrlBuilder urlBuilder) {
        urlBuilder.addSegment(segment);
        if (parameters != null){
            for(Map.Entry<String,String> parameter : parameters) {
                String key = parameter.getKey();
                String value = parameter.getValue();
                urlBuilder.addParam(key, value);
            }
        }

        urlBuilder.addParam("key", apiKey);
    }
}
