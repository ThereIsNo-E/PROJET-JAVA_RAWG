package org.main.services;

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

        if(request.getPlatforms() != null) {
            for(String platform : request.getPlatforms()) {
                if(platform != null && !platform.isEmpty()) {
                    parameters.add(entry("platform", platform));
                }
            }
        }

        if(request.getGenres() != null) {
            for(String genre : request.getGenres()) {
                if(genre != null && !genre.isEmpty()) {
                    parameters.add(entry("genre", genre));
                }
            }
        }

        if(request.getStores() != null) {
            for(String stores : request.getStores()) {
                if(stores != null && !stores.isEmpty()) {
                    parameters.add(entry("stores", stores));
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
