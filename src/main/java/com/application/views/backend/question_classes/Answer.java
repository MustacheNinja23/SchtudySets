package com.application.views.backend.question_classes;

import java.util.stream.Stream;

/*
    Associated with an instance of the Question class

    Allows for comparison with another Answer object, uses an
    algorithm to determine if the answers are similar enough
*/
public class Answer implements Comparable<Answer> {
    private final double[] answer;
    private final String questionType;

    public Answer(String quesType, Double[] ans) {
        answer = Stream.of(ans).mapToDouble(Double::doubleValue).toArray();
        questionType = quesType;
    }

    @Override
    public int compareTo(Answer other) {
        boolean isValid = false;
        switch (questionType) {
            case "open":
                // For the case of an open answer, a number within a range of 1 from the correct answer is considered a rounding error
                isValid = other.getAnswer()[0] >= this.answer[0] - 1 && other.getAnswer()[0] <= this.answer[0] + 1;
                break;
            case "vector":
                // For the case of a vector answer, the angle of each vector should be within a range of pi/18 (10 degrees)
                for (int i = 0; i < answer.length; i++) {
                    try {
                        isValid = other.getAnswer()[i] >= answer[i] - Math.PI / 18 && other.getAnswer()[i] <= answer[i] + Math.PI / 18;
                    } catch (IndexOutOfBoundsException e) {
                        return -1;
                    }
                }
                break;
        }

        // If the provided answer is determined to be valid, return 0, else return -1
        return isValid ? 0 : -1;
    }

    public String toString() {
        return answer[0] + "";
    }

    public double[] getAnswer() {
        return answer;
    }
}
