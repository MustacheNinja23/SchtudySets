package com.application.views.backend.question_classes;

import com.github.javaparser.quality.NotNull;

public class Answer implements Comparable<Answer>{
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
