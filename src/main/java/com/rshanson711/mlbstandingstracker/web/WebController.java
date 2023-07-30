package com.rshanson711.mlbstandingstracker.web;

import com.rshanson711.mlbstandingstracker.domain.DivisionEnum;
import com.rshanson711.mlbstandingstracker.domain.DivisionStanding;
import com.rshanson711.mlbstandingstracker.service.MlbApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    private MlbApiService mlbApiService;

    @GetMapping("/")
    public String home(@RequestParam(value = "leagueId", defaultValue = "104") int leagueId, @RequestParam(value = "divisionId", defaultValue = "205") int divisionId, Model model) {
        String[] leagueLeaders = mlbApiService.getLeagueLeaders();
        List<DivisionStanding> alDivisionStandings = new ArrayList<>();
        List<DivisionStanding> nlDivisionStandings = new ArrayList<>();
        alDivisionStandings.add(mlbApiService.getDivisionalStandings(103, DivisionEnum.AL_EAST));
        alDivisionStandings.add(mlbApiService.getDivisionalStandings(103, DivisionEnum.AL_CENTRAL));
        alDivisionStandings.add(mlbApiService.getDivisionalStandings(103, DivisionEnum.AL_WEST));

        nlDivisionStandings.add(mlbApiService.getDivisionalStandings(104, DivisionEnum.NL_EAST));
        nlDivisionStandings.add(mlbApiService.getDivisionalStandings(104, DivisionEnum.NL_CENTRAL));
        nlDivisionStandings.add(mlbApiService.getDivisionalStandings(104, DivisionEnum.NL_WEST));
        model.addAttribute("divisionStandings", Arrays.asList(alDivisionStandings, nlDivisionStandings));
        model.addAttribute("alLeader", leagueLeaders[0]);
        model.addAttribute("nlLeader", leagueLeaders[1]);
        return "home";
    }

    @GetMapping("/standings/nlcentral")
    public String nlCentral() {
        mlbApiService.getDivisionStandings();
        return "standings";
    }
}
