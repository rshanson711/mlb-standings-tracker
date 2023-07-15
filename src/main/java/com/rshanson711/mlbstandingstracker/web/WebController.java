package com.rshanson711.mlbstandingstracker.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(@RequestParam("team") String team, Model model) {
        model.addAttribute("team", team);
        return "home";
    }

}
