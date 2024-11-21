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
    private float rating;
    @Json(name = "genres")
    private List<GenreInfo> genreInfos;



    @Override
    public String toString() {
        return
                "title: " + title + '\n' +
                "release date: " + released + '\n' +
                "average rating: " + rating + '\n' +
                "genres: " + genreInfos.toString() + '\n' +
                "stores: " + storeWrappers.toString() + '\n' +
                "platforms: " + platformWrappers.toString() + '\n' +
                "average playtime: " + averagePlaytime + " hours" + '\n';
    }


}
