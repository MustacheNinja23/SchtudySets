package com.application.backend;

public class Identifier {
    private String type = "";
    private int diff;

    public Identifier(String t, int d) {
        type = t;
        diff = d;
    }

    public String getType() {
        return type;
    }

    public int getDiff() {
        return diff;
    }

    public boolean compareTo(Identifier other) {
        return diff == other.diff && type.equals(other.type);
    }
}
