package com.rshanson711.mlbstandingstracker.domain;

public class Division {
    private int id;
    private String name;

    private Division(String name) {
        this.name = name;
    }
    private Division() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
