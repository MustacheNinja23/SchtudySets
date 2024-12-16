package com.application.views.backend;

import com.application.views.backend.questionClasses.AllQuestions;
import com.application.views.backend.questionClasses.Identifier;
import com.application.views.backend.questionClasses.Question;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private HashMap<String, User> users;
    private final String gameNumber;
    private final ArrayList<Question> questions;

    public Game(String gameNumber, Identifier id, int numberOfQuestions) {
        users = new HashMap<>();
        questions = AllQuestions.createListOfQuestions(id, numberOfQuestions);
        this.gameNumber = gameNumber;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public String getGameNumber() {
        return gameNumber;
    }

    public void addUser(User user) {
        for(User u : users.values()) {
            if(user.getNickName().equals(u.getNickName())) {

                return;
            }
        }
        users.put(user.getNickName(), user);
    }
}
