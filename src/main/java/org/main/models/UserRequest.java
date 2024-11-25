package org.main.models;

import java.util.List;

// Fait le lien entre le controller et le gameService
public class UserRequest {
    private String name;
    private List<String> platforms;
    private List<String> genres;
    private List<String> stores;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getStores() {
        return stores;
    }

    public void addPlatform(String platform) {
        platforms.add(platform);
    }

    public void addGenre(String genre) {
        genres.add(genre);
    }

    public void addStore(String store) {
        stores.add(store);
    }

    public void reset() {
        name = null;
        platforms.clear();
        genres.clear();
        stores.clear();
    }


}
