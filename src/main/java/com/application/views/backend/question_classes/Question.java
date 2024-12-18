package com.application.views.backend.question_classes;

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

    public Answer getAnswer(){
        return ans;
    }

    public String getQues(){
        return ques;
    }

    public String[] getType(){
        return id.getTypes();
    }

    public String getDiff(){
        return id.getDiff();
    }

    public String getImageAdd(){
        return img;
    }

    public String toString(){
        return "{ " + ques + " : " + id.toString() + " : " + ans.toString() + " : " + img + " }";
    }
}
