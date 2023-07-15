package com.rshanson711.mlbstandingstracker.service;

import com.rshanson711.mlbstandingstracker.domain.MlbApiResponse;
import com.rshanson711.mlbstandingstracker.domain.Team;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    public String getDivisionLeader(int leagueId, int divisionId) {
        MlbApiResponse response = webClient.get().uri("https://statsapi.mlb.com/api/v1/standings?leagueId=" + leagueId).retrieve().bodyToMono(MlbApiResponse.class).block();
        return switch(divisionId) {
            case 201, 204 -> response.getRecords().get(0).getTeamRecords().get(0).getTeam().getName();
            case 202, 205 -> response.getRecords().get(1).getTeamRecords().get(0).getTeam().getName();
            case 203, 206 -> response.getRecords().get(2).getTeamRecords().get(0).getTeam().getName();
            default -> "No divisonal leader found";
        };
    }
}