package com.rshanson711.mlbstandingstracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MlbApiResponse {
    private String copyright;
    private List<Team> teams;
    private List<StandingsRecord> records;

    public MlbApiResponse() {}

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<StandingsRecord> getRecords() {
        return records;
    }

    public void setRecords(List<StandingsRecord> records) {
        this.records = records;
    }
}
