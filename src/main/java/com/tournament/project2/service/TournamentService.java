package com.tournament.project2.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.tournament.project2.tournament.model.Match;
import com.tournament.project2.tournament.model.Team;
import com.tournament.project2.tournament.model.Tournament;
import com.tournament.project2.tournament.TournamentScheduler;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import com.tournament.project2.util.Formatter;
import java.util.*;

@Component
public class TournamentService
{
    @Autowired
    TournamentScheduler tournamentScheduler ;

    //    @Autowired
//    Tournament tournament;
    public List<Match> retrieveTournamentSchedule() throws Exception
    {
        return tournamentScheduler.getMatchSchedule(createDefaultTournament());
    }

    public List<Match> retrieveTournamentSchedule(List<String> teams) throws Exception
    {
        return tournamentScheduler.getMatchSchedule(createTournament(teams));
    }
    public List<Match> retrieveTournamentSchedule(String teams) throws Exception
    {
        return tournamentScheduler.getMatchSchedule(createTournament(teams));
    }

    public String getTournamentSchedule(String locale)  throws Exception
    {
        Locale l = new Locale(locale);
        ResourceBundle bundle = ResourceBundle.getBundle("Messages", l);
        Tournament tournament= createDefaultTournament();
        List<Match> matches= tournamentScheduler.getMatchSchedule(tournament);
        return getTournamentDetails(tournament,matches).replaceAll("Tournament",bundle.getString("Tournament"));
    }

    public ResourceBundleMessageSource messageSource()
    {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        source.setUseCodeAsDefaultMessage(true);
        return source;

    }

    public String getTournamentSchedule(List<String> teams) throws Exception
    {
        Tournament tournament= createTournament(teams);
        List<Match> matches= tournamentScheduler.getMatchSchedule(tournament);
        return getTournamentDetails(tournament,matches);
    }

    private Tournament getDefaultProperties() throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Tournament tournament= new Tournament();
        tournament.setTournamentName("Pro Kabaddi 2018");
        tournament.setStartDate(sdf.parse(("07/10/2018")));
        tournament.setMaxMatchInDay(2);
        tournament.setMinDayGapForTeam(1);
        return tournament;
    }
    private Tournament createDefaultTournament() throws Exception
    {
        Tournament tournament=getDefaultProperties();

        tournament.addTeam("Kings XI Punjab", "Mohali");
        tournament.addTeam("Delhi Daredevils", "Delhi");
        tournament.addTeam("Kolkata Knight Riders","Kolkata");
        tournament.addTeam("Pune Warriors","Pune");
        tournament.addTeam("Chennai Super Kings","Chennai");
        tournament.addTeam("Mumbai Indians","Mumbai");
        tournament.addTeam("Rajasthan Royals","Jaipur");
        tournament.addTeam("Royal Challengers Bangalore","Banglore");
        return tournament;
    }
    private Tournament createTournament(String inputs) throws Exception
    {
        Tournament tournament;
        tournament= getDefaultProperties();
        int count=0;
        Map<String,String> map = readJsonWithObjectMapper(inputs);
        if (map.size()<2) {
            throw new Exception();
        }
        else
        {
            Set teamSet= map.keySet();
            Iterator<String> iter = teamSet.iterator();
            while (iter.hasNext())
            {
                String teamName = iter.next();
                String cityName = map.get(teamName);
                if ( (cityName==null) || cityName.length()<1) {
                    cityName = "Team_" + ++count + "_City";
                }
                tournament.addTeam(teamName, cityName);
            }
        }
        return tournament;
    }
    private Tournament createTournament(List<String> teams) throws Exception
    {
        Tournament tournament;
        if (teams.size()<2) {
            throw new Exception();
        }
        else
        {
            tournament= getDefaultProperties();

            int count=0;
            for (String teamName : teams)
            {
                String cityName="";
                if (teamName.indexOf(":")>0)
                {
                    cityName= teamName.split(":")[1];
                }
                cityName = "Team_" + ++count +"_City";
                tournament.addTeam(teamName, cityName);
            }
        }
        return tournament;
    }
    private String getTournamentDetails(Tournament tournament, List<Match> matches) throws Exception
    {
        StringBuilder sb= new StringBuilder();

        sb.append(Formatter.StringFormatterRightPad("Tournament Name",25) +" :\t\t" + tournament.getTournamentName());
        sb.append("\n");
        sb.append(Formatter.StringFormatterRightPad("Tournament Start Date",25)+" :\t\t"+ tournament.getStartDate());
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        for (Match match : matches)
        {
            sb.append(match.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public Map<String,String> readJsonWithObjectMapper(String inputs) throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputs, Map.class);
    }

}
