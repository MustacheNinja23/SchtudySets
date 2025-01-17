package com.application.views.backend.question_classes;

import java.util.Arrays;

/*
    Contains a 'type' and a 'difficulty' for sorting and
    identifying Question objects
*/
public class Identifier {
    private final String[] types;
    private final String diff;

    public Identifier(String d, String... types) {
        diff = d;
        this.types = types;
    }

    public String[] getTypes() {
        return types;
    }

    public String getDiff() {
        return diff;
    }

    public boolean equals(Identifier other) {
        return diff.equals(other.diff) && Arrays.equals(types, other.types);
    }

    public String toString() {
        return Arrays.toString(types) + ", " + diff;
    }
}
