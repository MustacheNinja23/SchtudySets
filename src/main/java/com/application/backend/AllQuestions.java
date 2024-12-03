package com.application.backend;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AllQuestions {
    /*public static?*/ HashMap<Identifier, ArrayList<Question>> questions;
    String[] types = {  // List of all question types
        "Projectile Motion",

    };
    int[] diffs = {  //List of all difficulties as ints
        0,
        1,
        2,
    };
    ArrayList<Identifier> ids;

    public AllQuestions() {
        questions = new HashMap<>();

        //create ids for lists
        for(String t : types) {
            for(int d : diffs) {
                ids.add(new Identifier(t,d));
            }
        }

        //Create lists and add questions
        Scanner file = new Scanner("QUESTIONS.txt");
        for (Identifier id : ids) {
            questions.put(id, new ArrayList<Question>());
            questions.get(id).add(new Question(file.nextLine().replaceAll("\"", ""), id, new Answer(Double.parseDouble(file.nextLine())), file.nextLine()));
        }

        file.close();
    }

    // Likely need more versions of "createListOfQuestions"

    public ArrayList<Question> createListOfQuestions(Identifier id, int number) {
        ArrayList<Question> tempQuestions = questions.get(id);
        ArrayList<Question> list = new ArrayList<Question>();

        int rand;
        for(int i = 0; i < number; i++) {
            rand = (int)(Math.random() * tempQuestions.size());
            list.add(tempQuestions.get(rand));
            tempQuestions.remove(rand);
        }
        return list;
    }



    public ArrayList<Question> getQuestionList(Identifier id) {
        return questions.get(id);
    }

    public Question getRandomQuestion(Identifier id) {
        ArrayList<Question> temp = getQuestionList(id);
        return temp.get((int)(Math.random() * temp.size()));
    }
}
