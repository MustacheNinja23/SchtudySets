package com.application.views.backend.game_classes;

import com.vaadin.flow.server.VaadinSession;

public class User {
    public VaadinSession session;
    private String nickName;
    private String gameNumber;
    private int score = 0;

    public User(VaadinSession session, String nickName, String gameNumber) {
        this.session = session;
        this.nickName = nickName;
        this.gameNumber = gameNumber;
    }

    public VaadinSession getSession() {
        return session;
    }

    public String getNickName() {
        return nickName;
    }

    public String getGameNumber() {
        return gameNumber;
    }

    public int getScore() {return score;}

    public void updateScore(int s) {score += s;}
}
