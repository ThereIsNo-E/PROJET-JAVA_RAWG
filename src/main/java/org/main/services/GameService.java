package org.main.services;

import okhttp3.internal.platform.Platform;
import org.main.models.Game;
import org.main.models.UserRequest;
import org.main.repository.GameHttpRepo;
import org.main.utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class GameService {
    private final GameHttpRepo repo;

    public GameService(GameHttpRepo repo) {
        this.repo = repo;
    }


    public List<Game> fetchGames(UserRequest request) {
        Map<String,String> parameters = mapParameters(request);

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
    private Map<String,String> mapParameters(UserRequest request) {
        Map<String, String> parameters = new HashMap<>();

        if(request.getName() != null) {
            parameters.put("search", request.getName());
        }

        if(request.getPlatforms() != null && !request.getPlatforms().isEmpty()) {
            parameters.put("platforms", getCommaSeparatedIds(request.getPlatforms()));
        }

        if(request.getGenres() != null && !request.getGenres().isEmpty()) {
            parameters.put("genres", getCommaSeparatedIds(request.getGenres()));
        }

        if(request.getStores() != null && !request.getStores().isEmpty()) {
            parameters.put("stores", getCommaSeparatedIds(request.getStores()));
        }

        return parameters;
    }

    private static String getCommaSeparatedIds(List<? extends AbstractGameWrapper.Info> infos) {
        return infos.stream()
                .map(info -> String.valueOf(info.getId()))
                .collect(Collectors.joining(","));
    }

}
