package com.example.cstnu.model;

public enum Type {
    NORMAL("normal"),
    CONTINGENT("contingent"),
    CONSTRAINT("constraint");

    private final String value;

    private Type(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

}
