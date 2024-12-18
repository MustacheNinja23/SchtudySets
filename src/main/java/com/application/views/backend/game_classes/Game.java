package com.application.views.backend.game_classes;

import com.application.views.backend.question_classes.AllQuestions;
import com.application.views.backend.question_classes.Identifier;
import com.application.views.backend.question_classes.Question;

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

    public User[] getUsersAsList() {
        return users.values().toArray(new User[0]);
    }

    public User getUser(String name) {
        return users.get(name);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public int getNumberOfQuestions() {
        return questions.size();
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
