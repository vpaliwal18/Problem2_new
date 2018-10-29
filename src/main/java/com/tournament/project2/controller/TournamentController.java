package com.tournament.project2.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.tournament.project2.tournament.model.Match;
import com.tournament.project2.service.TournamentService;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class TournamentController
{
    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/tournament/schedule")
    public List<Match> getSchedule() throws Exception
    {
        return tournamentService.retrieveTournamentSchedule();
    }
    
    @GetMapping("/tournament")
    public List<Match> getSchedule(@RequestParam(value="teams") List<String> teams) throws Exception
    {
        return tournamentService.retrieveTournamentSchedule(teams);
    }


    @GetMapping("/tournament/detailwithjson")
    public List<Match> getTournamentSchedule(@RequestParam(value="teams") String teams) throws Exception
    {
        return tournamentService.retrieveTournamentSchedule(teams);
    }

    @GetMapping("/tournament/default")
    public String getScheduleMessage(@RequestHeader("Accept-Language") String locale) throws Exception
    {
        return tournamentService.getTournamentSchedule(locale);

    }

    @GetMapping("/tournament/detail")
    public String getScheduleMessage(@RequestParam(value="teams") List<String> teams) throws Exception
    {

        return tournamentService.getTournamentSchedule(teams);
    }
    
}
