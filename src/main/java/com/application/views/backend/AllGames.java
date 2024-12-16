package com.application.views.backend;

import java.util.HashMap;

public class AllGames {
    public static HashMap<String, Game> allGames;

    public static void instantiate() {
        allGames = new HashMap<>();
    }

    public static void addGame(Game game) {
        allGames.put(game.getGameNumber(), game);
    }

    public static Game getGame(String gameNumber) {
        return allGames.get(gameNumber);
    }
}
