package org.main.models;

import org.main.utils.GenreInfo;
import org.main.utils.PlatformInfo;
import org.main.utils.StoreInfo;

import java.util.ArrayList;
import java.util.List;

// Fait le lien entre le controller et le gameService
public class UserRequest {
    private String name;
    private int resultLimit = 10;
    private List<PlatformInfo> platforms = new ArrayList<>();
    private List<GenreInfo> genres = new ArrayList<>();
    private List<StoreInfo> stores = new ArrayList<>();

    public String getName() {
        return name;
    }

    public int getResultLimit() {
        return resultLimit;
    }

    public void setResultLimit(int resultLimit) {
        this.resultLimit = resultLimit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlatformInfo> getPlatforms() {
        return platforms;
    }

    public List<GenreInfo> getGenres() {
        return genres;
    }

    public List<StoreInfo> getStores() {
        return stores;
    }

    public void addPlatform(PlatformInfo platform) {
        platforms.add(platform);
    }

    public void addGenre(GenreInfo genre) {
        genres.add(genre);
    }

    public void addStore(StoreInfo store) {
        stores.add(store);
    }

    public void reset() {
        name = null;
        if(platforms != null) {
            platforms.clear();
        }
        if(genres != null) {
            genres.clear();
        }
        if(stores != null) {
            stores.clear();
        }
    }


}
