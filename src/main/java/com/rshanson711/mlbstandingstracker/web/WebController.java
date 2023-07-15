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
    public String home(@RequestParam(value = "team", defaultValue = "112") int team, Model model) {
        model.addAttribute("team", mlbApiService.getTeamName(team));
        return "home";
    }
}
