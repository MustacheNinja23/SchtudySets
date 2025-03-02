package com.application.views.backend.game_classes;

import com.application.views.backend.question_classes.AllQuestions;
import com.application.views.backend.question_classes.Identifier;
import com.application.views.backend.question_classes.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/*
    Contains all identifying information and a HashMap of users for a Game instance
*/
public class Game {
    private final HashMap<String, User> users;
    private final String gameNumber;
    private final ArrayList<Question> questions;
    private boolean gameIsStarted;

    public Game(String gameNumber, Identifier id, int numberOfQuestions) {
        users = new HashMap<>();
        //questions = AllQuestions.createListOfQuestions(id, numberOfQuestions);
        questions = AllQuestions.getNonRandomList();
        this.gameNumber = gameNumber;
        gameIsStarted = false;
    }

    public ArrayList<User> getUsersAsList() {
        return new ArrayList<>(users.values());
    }

    public ArrayList<User> getUsersAsSortedList() {
        ArrayList<User> userArrayList = getUsersAsList();
        Collections.sort(userArrayList);
        return userArrayList;
    }

    public void startGame() {
        gameIsStarted = true;
    }

    public boolean isStarted() {
        return gameIsStarted;
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
        users.put(user.getNickName(), user);
    }
}
