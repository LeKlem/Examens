package org.acme.optaplanner.domain;


public class Room {
    private String name;
    private int sits;
    public Room() {
    }

    public Room(String name, int sits) {
        this.sits = sits;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getSits() {
        return sits;
    }

    @Override
    public String toString() {
        return name;
    }

}