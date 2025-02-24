package com.application.views.backend.question_classes;

import com.github.javaparser.quality.NotNull;

/*
    Associated with an instance of the Question class

    Allows for comparison with another Answer object, uses an
    algorithm to determine if the answers are similar enough
*/
public class Answer implements Comparable<Answer>{ //TODO: Comparing answers
    private double answer;

    public Answer(double ans) {
        answer = ans;
    }

    @Override
    public int compareTo(@NotNull Answer other) {
        boolean isValid = false;

        // If the provided answer is within a range of 1 from the correct answer assume it to be a rounding error
        if(other.getAnswer() >= this.answer - 1 && other.getAnswer() <= this.answer + 1) {
            isValid = true;
        }

        // If the provided answer is determined to be valid, return 0, else return -1
        return isValid ? 0 : -1;
    }

    public String toString(){
        return answer + "";
    }

    public double getAnswer() {
        return answer;
    }
}
