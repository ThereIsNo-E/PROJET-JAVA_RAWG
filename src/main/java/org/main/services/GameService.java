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

        filterGenres(genres, games);
        filterStores(stores, games);
        filterPlatforms(platforms, games);

        return games;
    }

    private void filterGenres(List<GenreInfo> genres, List<Game> games) {
        // Filtrage des jeux sur la liste des genres
        if (genres != null && !genres.isEmpty()) {
            try {
                for (GenreInfo genre : genres) {
                    // On utilise un iterateur pour pouvoir modifier la liste en iterrant dessus simultanement
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
            } catch (Exception e) {
                System.err.println("Erreur sur le filtrage des genres:" + e.getMessage());
            }
        }
    }

    private void filterStores(List<StoreInfo> stores, List<Game> games) {
        if (stores != null && !stores.isEmpty()) {
            try{
                for(StoreInfo store : stores) {
                    Iterator<Game> iterator = games.iterator();
                    while (iterator.hasNext()) {
                        Game game = iterator.next();
                        boolean found = false;
                        // Etape additionnelle par rapport a genres car wrapper
                        List<StoreWrapper> storeWrappers = game.getStoreWrappers();

                        for (StoreWrapper storeWrapper : storeWrappers) {
                            if(storeWrapper.storeInfo.getId() == store.getId()) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            iterator.remove();
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Erreur sur le filtrage des stores:" + e.getMessage());
            }
        }
    }

    private void filterPlatforms(List<PlatformInfo> platforms, List<Game> games) {
        if (platforms != null && !platforms.isEmpty()) {
            try{
                for(PlatformInfo platform : platforms) {
                    Iterator<Game> iterator = games.iterator();
                    while (iterator.hasNext()) {
                        Game game = iterator.next();
                        boolean found = false;
                        List<PlatformWrapper> platformWrappers = game.getPlatformWrappers();

                        for (PlatformWrapper platformWrapper : platformWrappers) {
                            if(platformWrapper.platformInfo.getId() == platform.getId()) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            iterator.remove();
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Erreur sur le filtrage des platforms:" + e.getMessage());
            }
        }
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
