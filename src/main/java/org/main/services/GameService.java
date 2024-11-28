package org.main.services;

import okhttp3.internal.platform.Platform;
import org.main.models.Game;
import org.main.models.UserRequest;
import org.main.repository.GameHttpRepo;
import org.main.utils.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameService {
    private final GameHttpRepo repo;

    public GameService(GameHttpRepo repo) {
        this.repo = repo;
    }


    public List<Game> fetchGames(UserRequest request) {
        List<Map.Entry<String,String>> parameters = mapParameters(request);
        return repo.fetchGames(parameters);
    }

    public List<GenreInfo> fetchGenres() {
        return repo.fetchEntityList(GenreResponse.class);
    }

    public List<PlatformInfo> fetchPlatforms() {
        return repo.fetchEntityList(PlatformResponse.class);
    }

    public List<StoreInfo> fetchStores() {
        return repo.fetchEntityList(StoreResponse.class);
    }

    // Méthode pour convertir une UserRequest en Map pour utilisation dans repository
    private List<Map.Entry<String, String>> mapParameters(UserRequest request) {
        List<Map.Entry<String, String>> parameters = new ArrayList<>();

        if(request.getName() != null) {
            parameters.add(entry("search", request.getName()));
        }

        if(request.getPlatforms() != null && !request.getPlatforms().isEmpty()) {
            for(PlatformInfo platform : request.getPlatforms()) {
                if(platform != null) {
                    parameters.add(entry("platforms", String.valueOf(platform.getId())));
                }
            }
        }

        if(request.getGenres() != null && !request.getGenres().isEmpty()) {
            for(GenreInfo genre : request.getGenres()) {
                if(genre != null) {
                    parameters.add(entry("genres", String.valueOf(genre.getId())));
                }
            }
        }

        if(request.getStores() != null && !request.getStores().isEmpty()) {
            for(StoreInfo stores : request.getStores()) {
                if(stores != null) {
                    parameters.add(entry("stores", String.valueOf(stores.getId())));
                }
            }
        }

        return parameters;
    }

    // Méthode pour simplifier l'ajout d'entrées dans la list de map
    private static Map.Entry<String, String> entry(String key, String value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }
}
