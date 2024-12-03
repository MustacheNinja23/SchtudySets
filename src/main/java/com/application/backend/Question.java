package com.application.backend;

import java.io.Serializable;

public class Question {
    String ques;
    Identifier id;
    Answer ans;
    String img;

    public Question(String s, Identifier i, Answer a, String ia){
        ques = s;
        id = i;
        ans = a;
        img = ia;
    }

    public String getQues(){
        return ques;
    }

    public String getType(){
        return id.getType();
    }

    public int getDiff(){
        return id.getDiff();
    }

    public String getImageAdd(){
        return img;
    }

    public String getDiffAsString(){
        switch(id.getDiff()){
            case 0:
                return "On level";
            case 1:
                return "Honors";
            case 2:
                return "AP";
            default:
                return "";
        }
    }
}
