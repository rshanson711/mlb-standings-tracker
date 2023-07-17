package com.rshanson711.mlbstandingstracker.web;

import com.rshanson711.mlbstandingstracker.service.MlbApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    @Autowired
    private MlbApiService mlbApiService;

    @GetMapping("/")
    public String home(@RequestParam(value = "leagueId", defaultValue = "104") int leagueId, @RequestParam(value = "divisionId", defaultValue = "205") int divisionId, Model model) {
        String[] leagueLeaders = mlbApiService.getLeagueLeaders();
        model.addAttribute("alLeader", leagueLeaders[0]);
        model.addAttribute("nlLeader", leagueLeaders[1]);
        model.addAttribute("alDivisionalStandingsTeams", mlbApiService.getDivisionalStandingsNames(103));
        model.addAttribute("alDivisionalStandingsWins", mlbApiService.getDivisionalStandingsWins(103));
        model.addAttribute("alDivisionalStandingsLosses", mlbApiService.getDivisionalStandingsLosses(103));
        model.addAttribute("alDivisionalStandingsWinPercentages", mlbApiService.getDivisionalStandingsWinPercentages(103));
        model.addAttribute("alDivisionalStandingsGamesBehind", mlbApiService.getDivisionalStandingsGamesBehind(103));
        model.addAttribute("nlDivisionalStandingsTeams", mlbApiService.getDivisionalStandingsNames(104));
        return "home";
    }

    @GetMapping("/standings/nlcentral")
    public String nlCentral() {
        mlbApiService.getDivisionStandings();
        return "standings";
    }
}
