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
        List<Game> games = repo.fetchGames(parameters);
        List<GenreInfo> genres = request.getGenres();
        List<StoreInfo> stores = request.getStores();
        List<PlatformInfo> platforms = request.getPlatforms();

        // On utilise un iterateur pour pouvoir modifier la liste en iterrant dessus simultanement
        if (genres != null && !genres.isEmpty()) {
            for (GenreInfo genre : genres) {
                Iterator<Game> iterator = games.iterator();
                while (iterator.hasNext()) {
                    Game game = iterator.next();
                    boolean found = false;
                    for (GenreInfo gameGenre : game.getGenreInfos()) {
                        if (genre.getId() == gameGenre.getId()) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        iterator.remove();
                    }
                }
            }
        }


        if(stores != null && !stores.isEmpty()) {
            for(StoreInfo store : stores) {
                StoreWrapper storeWrapper = new StoreWrapper(store);
                games.removeIf(game -> !game.getStoreWrappers().contains(storeWrapper));
            }
        }

        if(platforms != null && !platforms.isEmpty()) {
            for(PlatformInfo platform : platforms) {
                PlatformWrapper platformWrapper = new PlatformWrapper(platform);
                games.removeIf(game -> !game.getPlatformWrappers().contains(platformWrapper));
            }
        }

        return games;
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

    // Méthode pour concaténer les ids des objets en une seule chaîne de caractères
    private static String getCommaSeparatedIds(List<? extends AbstractGameWrapper.Info> infos) {
        return infos.stream()
                .map(info -> String.valueOf(info.getId()))
                .collect(Collectors.joining(","));
    }

}
