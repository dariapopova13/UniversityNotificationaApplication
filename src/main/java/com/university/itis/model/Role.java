package com.university.itis.model;

public enum Role {

    DEAN, PROFESSOR;

    public static Role getRole(String name) {
        if (name.equalsIgnoreCase(DEAN.name()))
            return DEAN;
        else return PROFESSOR;
    }

}
