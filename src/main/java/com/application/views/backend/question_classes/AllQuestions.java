package com.application.views.backend.question_classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/*
    Contains a HashMap of ArrayLists, each of which contains
    all questions sharing an Identifier with the List
*/
public class AllQuestions {
    public static HashMap<Identifier, ArrayList<Question>> questions;
    public static String[] types = {  // List of all question types
        "Projectile Motion",

    };
    public static String[] diffs = {  //List of all difficulties
        "On-level",
        "Honors",
        "AP"
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
            for(String d : diffs) {
                ids.add(new Identifier(d,t));
            }
        }

        Iterator<String> iterator = fileList.iterator();
        iterator.next();
        String temp = iterator.next();
        for(Identifier id : ids) {
            questions.put(id, new ArrayList<Question>());

            do{
                if(temp.charAt(0) == '*') {
                    break;
                }

                questions.get(id).add(
                    new Question(
                        temp,
                        id,
                        new Answer(Double.parseDouble(iterator.next())),
                        iterator.next()
                    )
                );
                iterator.next();  //skip empty lines
                temp = iterator.next();
            } while(temp.charAt(0) != '*');
        }
    }

    //TODO: Likely need more versions of "createListOfQuestions"

    public static ArrayList<Question> createListOfQuestions(Identifier id, int number) {
        ArrayList<Question> tempQuestions;
        ArrayList<Question> list;
        
        for(Identifier d : ids) {
            if(id.equals(d)) {
                tempQuestions = questions.get(d);
                list = new ArrayList<Question>();

                int rand;
                for(int i = 0; i < number; i++) {
                    rand = (int)(Math.random() * tempQuestions.size());
                    list.add(tempQuestions.get(rand));
                    tempQuestions.remove(rand);
                }
                return list;
            }
        }
        return null;
    }



    public static ArrayList<Question> getQuestionTypeList(Identifier id) {
        return questions.get(id);
    }

    public static Question getRandomQuestion(Identifier id) {
        ArrayList<Question> temp = getQuestionTypeList(id);
        return temp.get((int)(Math.random() * temp.size()));
    }
}
