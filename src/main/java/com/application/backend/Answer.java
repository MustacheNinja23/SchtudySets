package com.application.backend;

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
}
