package com.application.views.backend.question_classes;

import com.github.javaparser.quality.NotNull;

/*
    Associated with an instance of the Question class

    Allows for comparison with another Answer object, uses an
    algorithm to determine if the answers are similar enough
*/
public class Answer implements Comparable<Answer>{ //TODO: Comparing answers
    double answer;

    public Answer(double ans) {
        answer = ans;
    }

    @Override
    public int compareTo(@NotNull Answer o) {
        return 0;
    }

    public String toString(){
        return answer + "";
    }
}
