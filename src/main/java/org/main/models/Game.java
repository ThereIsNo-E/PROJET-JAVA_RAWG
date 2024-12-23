package org.main.models;
import com.squareup.moshi.Json;
import org.main.utils.GenreInfo;
import org.main.utils.PlatformWrapper;
import org.main.utils.StoreWrapper;

import java.util.List;

public class Game {
    @Json(name = "name")
    private String title;
    @Json(name = "playtime")
    private float averagePlaytime;
    @Json(name = "platforms")
    private List<PlatformWrapper> platformWrappers;
    @Json(name = "stores")
    private List<StoreWrapper> storeWrappers;
    private String released;
    @Json(name = "rating")
    private float userRating;
    @Json(name = "metacritic")
    // usage de la classe Integer pour valeur nullable
    private Integer criticRating;
    @Json(name = "genres")
    private List<GenreInfo> genreInfos;

    public String getTitle() {
        return title;
    }

    public String getReleased() {
        return released;
    }

    public float getUserRating() {
        return userRating;
    }

    public Integer getCriticRating() {
        return criticRating;
    }

    public float getAveragePlaytime() {
        return averagePlaytime;
    }
    public List<PlatformWrapper> getPlatformWrappers() {
        return platformWrappers;
    }

    public List<StoreWrapper> getStoreWrappers() {
        return storeWrappers;
    }

    public List<GenreInfo> getGenreInfos() {
        return genreInfos;
    }

    @Override
    public String toString() {
        return
                "title: " + title + '\n' +
                "release date: " + released + '\n' +
                "average user rating: " + userRating + '\n' +
                "metacritic rating: " + criticRating + '\n' +
                "genres: " + genreInfos.toString() + '\n' +
                "stores: " + storeWrappers.toString() + '\n' +
                "platforms: " + platformWrappers.toString() + '\n' +
                "average playtime: " + averagePlaytime + " hours" + '\n';
    }


}
