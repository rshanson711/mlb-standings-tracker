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
}