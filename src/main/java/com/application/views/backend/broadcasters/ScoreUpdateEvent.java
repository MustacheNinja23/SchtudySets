package com.application.views.backend.broadcasters;

import com.application.views.backend.game_classes.User;

public class ScoreUpdateEvent {
    private User user;

    public ScoreUpdateEvent(User user) {
        this.user = user;
    }

    public void updateScore(int score) {
        //TODO: Score calculation
        user.updateScore(score);
    }

    public User getUser() {
        return user;
    }
}
