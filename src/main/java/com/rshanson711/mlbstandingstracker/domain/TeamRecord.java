package com.rshanson711.mlbstandingstracker.domain;

public class TeamRecord {
    private Team team;
    private String divisionRank;
    private int gamesPlayed;
    private String gamesBack;
    private String wildCardGamesBack;
    private int wins;
    private int losses;
    private String winningPercentage;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getDivisionRank() {
        return divisionRank;
    }

    public void setDivisionRank(String divisionRank) {
        this.divisionRank = divisionRank;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public String getGamesBack() {
        return gamesBack;
    }

    public void setGamesBack(String gamesBack) {
        this.gamesBack = gamesBack;
    }

    public String getWildCardGamesBack() {
        return wildCardGamesBack;
    }

    public void setWildCardGamesBack(String wildCardGamesBack) {
        this.wildCardGamesBack = wildCardGamesBack;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getWinningPercentage() {
        return winningPercentage;
    }

    public void setWinningPercentage(String winningPercentage) {
        this.winningPercentage = winningPercentage;
    }
}
