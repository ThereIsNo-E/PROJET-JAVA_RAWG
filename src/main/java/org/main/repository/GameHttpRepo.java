package org.main.repository;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import com.squareup.moshi.Types;
import org.main.client.ApiClient;
import org.main.utils.*;
import org.main.models.Game;

import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.lang.reflect.Type;

public class GameHttpRepo {
    private final ApiClient apiClient;
    private final Moshi moshi;

    public GameHttpRepo() {
        this.apiClient = ApiClient.getInstance();
        this.moshi = new Moshi.Builder().build();
    }

    // méthode avec abstraction du type de réponse pour généraliser
    // la méthode
    public <T, R extends AbstractResponse<T>> List<T> fetchEntityList(Class<R> responseType) {
        // Map entre type de retour et segment nécessaire
        Map<Class<?>, String> segmentMap = Map.of(
                GenreResponse.class, "genres",
                PlatformResponse.class, "platforms",
                StoreResponse.class, "stores"
        );

        String segment = segmentMap.get(responseType);
        if(segment == null) {
            throw new IllegalArgumentException("Type de réponse inconnu : " + responseType);
        }

        try {
            String jsonResponse = apiClient.get(null, segment);
            JsonAdapter<R> jsonAdapter = moshi.adapter(responseType);
            R response = jsonAdapter.fromJson(jsonResponse);
            if(response != null && response.getResults() != null) {
                return response.getResults();
            }
            else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public List<GenreInfo> fetchGenres() {
//        try {
//            String jsonResponse = apiClient.get(null,"genres");
//            JsonAdapter<GenreResponse> jsonAdapter = moshi.adapter(GenreResponse.class);
//            GenreResponse genreResponse = jsonAdapter.fromJson(jsonResponse);
//            if(genreResponse != null && genreResponse.getResults() != null) {
//                return genreResponse.getResults();
//            }
//            else {
//                return null;
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // Gestion de la requête
    public List<Game> fetchGames(List<Map.Entry<String,String>> parameters) {
        try {
            // Obtention de la réponse json par la classe apiClient
            String jsonResponse = apiClient.get(parameters,"games");
            // Utilisation de moshi pour déserialiser la réponse
            JsonAdapter<GameResponse> jsonAdapter = moshi.adapter(GameResponse.class);
            GameResponse gameResponse = jsonAdapter.fromJson(jsonResponse);
            if(gameResponse != null && gameResponse.getResults() != null) {
                return gameResponse.getResults();
            }
            else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
