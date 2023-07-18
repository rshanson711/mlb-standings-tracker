package com.rshanson711.mlbstandingstracker.service;

import com.rshanson711.mlbstandingstracker.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class MlbApiService {

    public WebClient webClient = WebClient.builder().build();

    public String getTeamName(int teamNum) {
        MlbApiResponse response = webClient.get().uri("https://statsapi.mlb.com/api/v1/teams/" + teamNum).retrieve().bodyToMono(MlbApiResponse.class).block();
        Team team = response.getTeams().get(0);
        if (team != null) {
            return team.getName();
        }
        return "Team not found";
    }

    public void getDivisionStandings() {
        MlbApiResponse response = webClient.get().uri("https://statsapi.mlb.com/api/v1/standings?leagueId=104").retrieve().bodyToMono(MlbApiResponse.class).block();
        int a = 2;
    }

//    public String getLeaders(int leagueId, int divisionId) {
//        MlbApiResponse response = webClient.get().uri("https://statsapi.mlb.com/api/v1/standings?leagueId=" + leagueId).retrieve().bodyToMono(MlbApiResponse.class).block();
//        return switch(divisionId) {
//            case 201, 204 -> response.getRecords().get(0).getTeamRecords().get(0).getTeam().getName();
//            case 202, 205 -> response.getRecords().get(1).getTeamRecords().get(0).getTeam().getName();
//            case 203, 206 -> response.getRecords().get(2).getTeamRecords().get(0).getTeam().getName();
//            default -> "No divisonal leader found";
//        };
//    }

    public String[] getLeagueLeaders() {
        String[] output = new String[2];
        MlbApiResponse response = webClient.get().uri("https://statsapi.mlb.com/api/v1/standings?leagueId=103").retrieve().bodyToMono(MlbApiResponse.class).block();
        output[0] = response.getRecords().get(0).getTeamRecords().get(0).getTeam().getName();
        response = webClient.get().uri("https://statsapi.mlb.com/api/v1/standings?leagueId=104").retrieve().bodyToMono(MlbApiResponse.class).block();
        output[1] = response.getRecords().get(0).getTeamRecords().get(0).getTeam().getName();
        return output;
    }

    public String[] getDivisionalStandingsNames(int leagueId) {
        String[] output = new String[15];
        MlbApiResponse response = webClient.get().uri("https://statsapi.mlb.com/api/v1/standings?leagueId=" + leagueId).retrieve().bodyToMono(MlbApiResponse.class).block();

        for (int j = 0; j < 3; j++) {
            List<TeamRecord> teamRecordList = response.getRecords().get(j).getTeamRecords();
            for (int i = 0; i < 5; i++) {
                output[i + (5 * j)] = teamRecordList.get(i).getTeam().getName();
            }
        }

        return output;
    }

    public Integer[] getDivisionalStandingsWins(int leagueId) {
        Integer[] output = new Integer[15];
        MlbApiResponse response = webClient.get().uri("https://statsapi.mlb.com/api/v1/standings?leagueId=" + leagueId).retrieve().bodyToMono(MlbApiResponse.class).block();

        for (int j = 0; j < 3; j++) {
            List<TeamRecord> teamRecordList = response.getRecords().get(j).getTeamRecords();
            for (int i = 0; i < 5; i++) {
                output[i + (5 * j)] = teamRecordList.get(i).getWins();
            }
        }

        return output;
    }

    public Integer[] getDivisionalStandingsLosses(int leagueId) {
        Integer[] output = new Integer[15];
        MlbApiResponse response = webClient.get().uri("https://statsapi.mlb.com/api/v1/standings?leagueId=" + leagueId).retrieve().bodyToMono(MlbApiResponse.class).block();

        for (int j = 0; j < 3; j++) {
            List<TeamRecord> teamRecordList = response.getRecords().get(j).getTeamRecords();
            for (int i = 0; i < 5; i++) {
                output[i + (5 * j)] = teamRecordList.get(i).getLosses();
            }
        }

        return output;
    }

    public String[] getDivisionalStandingsWinPercentages(int leagueId) {
        String[] output = new String[15];
        MlbApiResponse response = webClient.get().uri("https://statsapi.mlb.com/api/v1/standings?leagueId=" + leagueId).retrieve().bodyToMono(MlbApiResponse.class).block();

        for (int j = 0; j < 3; j++) {
            List<TeamRecord> teamRecordList = response.getRecords().get(j).getTeamRecords();
            for (int i = 0; i < 5; i++) {
                output[i + (5 * j)] = teamRecordList.get(i).getWinningPercentage();
            }
        }

        return output;
    }

    public String[] getDivisionalStandingsGamesBehind(int leagueId) {
        String[] output = new String[15];
        MlbApiResponse response = webClient.get().uri("https://statsapi.mlb.com/api/v1/standings?leagueId=" + leagueId).retrieve().bodyToMono(MlbApiResponse.class).block();

        for (int j = 0; j < 3; j++) {
            List<TeamRecord> teamRecordList = response.getRecords().get(j).getTeamRecords();
            for (int i = 0; i < 5; i++) {
                output[i + (5 * j)] = teamRecordList.get(i).getGamesBack();
            }
        }

        return output;
    }

//    public List<TeamRecord> getDivisionalStandings(int leagueId, DivisionEnum divisionEnum) {
//        MlbApiResponse response = webClient.get().uri("https://statsapi.mlb.com/api/v1/standings?leagueId=" + leagueId).retrieve().bodyToMono(MlbApiResponse.class).block();
//        return switch (divisionEnum) {
//            case AL_EAST, NL_EAST -> response.getRecords().get(0).getTeamRecords();
//            case AL_CENTRAL, NL_CENTRAL -> response.getRecords().get(1).getTeamRecords();
//            case AL_WEST, NL_WEST -> response.getRecords().get(2).getTeamRecords();
//        };
//    }

    public DivisionStanding getDivisionalStandings(int leagueId, DivisionEnum divisionEnum) {
        MlbApiResponse response = webClient.get().uri("https://statsapi.mlb.com/api/v1/standings?leagueId=" + leagueId).retrieve().bodyToMono(MlbApiResponse.class).block();
        return switch (divisionEnum) {
            case AL_EAST, NL_EAST -> new DivisionStanding(divisionEnum.getName(), response.getRecords().get(0).getTeamRecords());
            case AL_CENTRAL, NL_CENTRAL -> new DivisionStanding(divisionEnum.getName(), response.getRecords().get(1).getTeamRecords());
            case AL_WEST, NL_WEST -> new DivisionStanding(divisionEnum.getName(), response.getRecords().get(2).getTeamRecords());
        };
    }
}