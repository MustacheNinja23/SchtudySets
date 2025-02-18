package com.application.views.backend.game_classes;

import com.github.javaparser.quality.NotNull;
import com.vaadin.flow.server.VaadinSession;

/*
    Contains identifying information for an instance of the
    User class associated with an active session
*/
public class User implements Comparable<User> {
    public VaadinSession session;
    private final String nickName;
    private final String gameNumber;
    private int score = 0;

    public User(String nickName, String gameNumber) {
        this.nickName = nickName;
        this.gameNumber = gameNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public String getGameNumber() {
        return gameNumber;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int s) {
        score += s;
    }

    @Override
    public int compareTo(@NotNull User other) {
        return Integer.compare(other.getScore(), this.score);
    }
}
