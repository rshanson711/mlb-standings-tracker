package com.rshanson711.mlbstandingstracker.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rshanson711.mlbstandingstracker.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Random;

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

    public String getLastGamePk(int teamId) throws IOException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        URI uri = new URI("https://statsapi.mlb.com/api/v1/teams/" + teamId + "?hydrate=previousSchedule");


        JsonNode dates = mapper.readTree(uri.toURL()).get("teams").get(0).get("previousGameSchedule").get("dates"); // GET SET OF DATES WHERE THEY LAST PLAYED (IN ASC ORDER) (ACCOUNTS FOR WEIRD MULTI GAME DAYS)
        JsonNode games = dates.get(dates.size() - 1).get("games"); // GET GAMES ON LAST DATE
        return games.get(games.size() - 1).get("gamePk").asText(); // RETURN FINAL GAME ON LAST DATE (ACCOUNTS FOR DOUBLEHEADERS, ETC)
    }

    public String getLastGameHighlightVideo(int teamId) throws IOException, URISyntaxException {
        String gamePk = getLastGamePk(teamId);
        ObjectMapper mapper = new ObjectMapper();
        URI uri = new URI("https://statsapi.mlb.com/api/v1/game/" + gamePk + "/content");

        JsonNode highlights = mapper.readTree(uri.toURL()).get("highlights").get("highlights").get("items"); // GET HIGHLIGHT ITEMS
        return highlights.get(0).get("playbacks").get(0).get("url").asText(); // GET URL OF FIRST HIGHLIGHT
    }
}