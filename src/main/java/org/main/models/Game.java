package org.main.models;
import com.squareup.moshi.Json;
import org.main.services.Genre;
import org.main.services.Platform;
import org.main.services.Store;

import java.util.List;

public class Game {
    @Json(name = "name")
    private String title;
    @Json(name = "playtime")
    private float averagePlaytime;
    @Json(name = "platforms")
    private List<Platform> platforms;
    @Json(name = "stores")
    private List<Store> stores;
    private String released;
    private float rating;
    @Json(name = "genres")
    private List<Genre> genres;



    @Override
    public String toString() {
        return
                "title: " + title + '\n' +
                "release date: " + released + '\n' +
                "average rating: " + rating + '\n' +
                "genres: " + genres.toString() + '\n' +
                "stores: " + stores.toString() + '\n' +
                "platforms: " + platforms.toString() + '\n' +
                "average playtime: " + averagePlaytime + " hours" + '\n';
    }


}
