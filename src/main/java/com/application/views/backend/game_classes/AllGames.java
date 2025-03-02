package com.application.views.backend.game_classes;

import java.util.HashMap;

/*
    Server side class built around the HashMap allGames,
    which contains all instances of the Game class
*/
public class AllGames {
    public static HashMap<String, Game> allGames;

    public static void instantiate() {
        allGames = new HashMap<>();
    }

    public static void addGame(Game game) {
        allGames.put(game.getGameNumber(), game);
        System.out.println("created game " + game.getGameNumber());
    }

    public static Game getGame(String gameNumber) {
        return allGames.get(gameNumber);
    }

    public static void printAllGames() {
        for (Game game : allGames.values()) {
            System.out.print(game.getGameNumber() + ", ");
        }
    }
}
