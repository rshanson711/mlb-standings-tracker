package com.rshanson711.mlbstandingstracker.domain;

import java.util.List;

public class StandingsRecord {
    private League league;
    private Division division;
    private List<TeamRecord> teamRecords;

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public List<TeamRecord> getTeamRecords() {
        return teamRecords;
    }

    public void setTeamRecords(List<TeamRecord> teamRecords) {
        this.teamRecords = teamRecords;
    }
}
