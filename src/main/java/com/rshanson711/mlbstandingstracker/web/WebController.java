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
        model.addAttribute("team", mlbApiService.getDivisionLeader(leagueId, divisionId));
        return "home";
    }

    @GetMapping("/standings/nlcentral")
    public String nlCentral() {
        mlbApiService.getDivisionStandings();
        return "standings";
    }
}
