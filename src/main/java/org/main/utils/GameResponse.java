package org.main.utils;

import com.squareup.moshi.Json;
import org.main.models.Game;

import java.util.List;

// Wrapper class pour Game
public class GameResponse {
    @Json(name = "results")
    List<Game> games;

    public List<Game> getGames() {
        return games;
    }
}
