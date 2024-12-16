package com.application.views.backend;

import com.vaadin.flow.server.VaadinSession;

public class User {
    public VaadinSession session;
    private String nickName;
    private String gameNumber;

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
}
