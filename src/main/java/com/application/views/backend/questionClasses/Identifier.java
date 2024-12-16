package com.application.views.backend.questionClasses;

import java.util.Arrays;

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

    public boolean compareTo(Identifier other) {
        return diff == other.diff && Arrays.equals(types, other.types);
    }

    public String toString() {
        return Arrays.toString(types) + ", " + diff;
    }
}
