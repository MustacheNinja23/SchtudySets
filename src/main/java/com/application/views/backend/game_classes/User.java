package com.application.views.backend.game_classes;

import com.vaadin.flow.server.VaadinSession;
/*
    Contains identifying information for an instance of the
    User class associated with an active session
*/
public class User {
    public VaadinSession session;
    private final String nickName;
    private final String gameNumber;
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
