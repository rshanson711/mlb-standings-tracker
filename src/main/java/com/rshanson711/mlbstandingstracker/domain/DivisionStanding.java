package com.rshanson711.mlbstandingstracker.domain;

import java.util.List;

public class DivisionStanding {
    private String name;
    private List<TeamRecord> teams;

    public DivisionStanding(String name, List<TeamRecord> teams) {
        this.name = name;
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TeamRecord> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamRecord> teams) {
        this.teams = teams;
    }
}
