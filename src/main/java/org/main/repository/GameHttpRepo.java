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

    public List<GenreInfo> fetchGenres() {
        try {
            String jsonResponse = apiClient.get(null,"genres");
            Type type = Types.newParameterizedType(List.class, GenreInfo.class);
            JsonAdapter<List<GenreInfo>> jsonAdapter = moshi.adapter(type);
            return jsonAdapter.fromJson(jsonResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Gestion de la requête
    public List<Game> fetchGames(List<Map.Entry<String,String>> parameters) {
        try {
            // Obtention de la réponse json par la classe apiClient
            String jsonResponse = apiClient.get(parameters,"games");
            // Utilisation de moshi pour déserialiser la réponse
            JsonAdapter<GameResponse> jsonAdapter = moshi.adapter(GameResponse.class);
            GameResponse gameResponse = jsonAdapter.fromJson(jsonResponse);
            if(gameResponse != null && gameResponse.getResults() != null) {
                // On rend le premier résultat qui sera celui qui correspond le mieux
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
