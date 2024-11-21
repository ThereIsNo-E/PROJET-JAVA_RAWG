package org.main.services;

import org.main.models.Game;
import org.main.models.UserRequest;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameService {

    public List<Game> fetchGames(UserRequest request) {
        List<Map.Entry<String,String>> parameters = mapParameters(request);

    }

    // Méthode pour convertir une UserRequest en Map pour utilisation dans repository
    private List<Map.Entry<String, String>> mapParameters(UserRequest request) {
        List<Map.Entry<String, String>> parameters = new ArrayList<>();

        if(request.getName() != null && !request.getName().isEmpty()) {
            parameters.add(entry("name", request.getName()));
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
