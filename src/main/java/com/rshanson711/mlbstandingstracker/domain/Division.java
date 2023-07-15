package com.rshanson711.mlbstandingstracker.domain;

public enum Division {
    NATIONAL_LEAGUE_CENTRAL ("NL Central");

    private String name;

    private int id;

    private Division(String name) {
        this.name = name;
    }

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
