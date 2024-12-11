package com.application.views.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class AllQuestions {
    public static HashMap<Identifier, ArrayList<Question>> questions;
    public static String[] types = {  // List of all question types
        "Projectile Motion",

    };
    public static int[] diffs = {  //List of all difficulties as ints
        0,
        1,
        2
    };
    public static ArrayList<Identifier> ids = new ArrayList<Identifier>();

    public static void instantiate() throws FileNotFoundException {
        questions = new HashMap<>();

        //Create lists and add questions
        Scanner file = new Scanner(new File("src/main/resources/QUESTIONS.txt"));
        ArrayList<String> fileList = new ArrayList<String>();
        while(file.hasNextLine()) {
            fileList.add(file.nextLine());
        }
        file.close();

        //create ids for lists & add questions
        for(String t : types) {
            for(int d : diffs) {
                ids.add(new Identifier(t,d));
            }
        }

        Iterator<String> iterator = fileList.iterator();
        iterator.next();
        for(Identifier id : ids) {
            questions.put(id, new ArrayList<Question>());

            do{
                questions.get(id).add(
                    new Question(
                        iterator.next(),
                        id,
                        new Answer(Double.parseDouble(iterator.next())),
                        iterator.next()
                    )
                );
                iterator.next();  //skip empty lines
            } while(iterator.next().charAt(0) != '*');
        }
    }

    // Likely need more versions of "createListOfQuestions"

    public static ArrayList<Question> createListOfQuestions(Identifier id, int number) {
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



    public static ArrayList<Question> getQuestionList(Identifier id) {
        return questions.get(id);
    }

    public static Question getRandomQuestion(Identifier id) {
        ArrayList<Question> temp = getQuestionList(id);
        return temp.get((int)(Math.random() * temp.size()));
    }
}
