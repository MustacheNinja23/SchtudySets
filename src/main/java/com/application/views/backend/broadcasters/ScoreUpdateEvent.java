package com.application.views.backend.broadcasters;

import com.application.views.backend.game_classes.User;

public class ScoreUpdateEvent {
    private User user;
    private int score;

    public ScoreUpdateEvent(User u, int s) {
        user = u;
        score = s;
    }

    public User getUser(){
        return user;
    }

    public int getScore(){
        return score;
    }

    //public int calculateScore(int prev, int next) {}
}
