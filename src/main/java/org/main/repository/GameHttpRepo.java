package org.main.repository;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.main.client.ApiClient;
import org.main.services.GameResponse;
import org.main.models.Game;

import java.util.List;
import java.io.IOException;

public class GameHttpRepo {
    private final ApiClient apiClient;
    private final Moshi moshi;


    public GameHttpRepo() {
        this.apiClient = ApiClient.getInstance();
        this.moshi = new Moshi.Builder().build();
    }

    // Formattage de la requête pour une recherche par nom
    public List<Game> fetchGamesByName(String name) {
        String url = "https://api.rawg.io/api/games?page=1&search=" + name;
        return urlToGames(url);
    }

    // Gestion de la requête
    private List<Game> urlToGames(String url) {
        try {
            // Obtention de la réponse json par la classe apiClient
            String jsonResponse = apiClient.get(url);
            // Utilisation de moshi pour déserialiser la réponse
            JsonAdapter<GameResponse> jsonAdapter = moshi.adapter(GameResponse.class);
            GameResponse gameResponse = jsonAdapter.fromJson(jsonResponse);
            if(gameResponse != null && gameResponse.getGames() != null) {
                // On rend le premier résultat qui sera celui qui correspond le mieux
                return gameResponse.getGames();
            }
            else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
