package com.application.views.backend.game_classes;

import java.util.HashMap;

public class AllGames {
    public static HashMap<String, Game> allGames;

    public static void instantiate() {
        allGames = new HashMap<>();
    }

    public static void addGame(Game game) {
        allGames.put(game.getGameNumber(), game);
        System.out.println(allGames.get(game.getGameNumber()).getGameNumber());
    }

    public static Game getGame(String gameNumber) {
        return allGames.get(gameNumber);
    }
}
