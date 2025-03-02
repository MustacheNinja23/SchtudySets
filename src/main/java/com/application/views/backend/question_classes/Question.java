package com.application.views.backend.question_classes;

/*
    Server side class containing the information for a single question

    Contains:
        - The question as a String
        - The associated Identifier instance
        - The associated Answer instance
        - A String containing a file path to an associated Image
*/
public class Question {
    String ques;
    Identifier id;
    Answer ans;
    String img;

    public Question(String s, Identifier i, Answer a, String ia) {
        ques = s;
        id = i;
        ans = a;
        img = ia;
    }

    public Answer getAnswer() {
        return ans;
    }

    public String getQues() {
        return ques;
    }

    public String[] getType() {
        return id.getTypes();
    }

    public String getQuestionType() {
        return id.getQuestionType();
    }

    public String getImageAddress() {
        return img;
    }

    public String toString() {
        return "{ " + getQuestionType() + " : " + ques + " : " + id.toString() + " : " + ans.toString() + " : " + img + " }";
    }
}
