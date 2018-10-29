package com.tournament.project2.tournament.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Queue;

@Component
public class Tournament
{
    private String tournamentName;
    private HashSet<Team> tournamentTeams;
    private Queue<Match> matches;
    private int maxMatchInDay;
    private int minDayGapForTeam;
    private Date startDate;
    private ArrayList<Match> scheduleMatches;

    public Tournament()
    {

    }
    public Tournament(String name, int totalRound)
    {
        this.tournamentName=name;
        this.tournamentTeams = new HashSet<Team>();
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public HashSet<Team> getTournamentTeams() {
        return tournamentTeams;
    }

    public void setTournamentTeams(HashSet<Team> tournamentTeams) {
        this.tournamentTeams = tournamentTeams;
    }

    public Queue<Match> getMatches() {
        return matches;
    }

    public void setMatches(Queue<Match> matches) {
        this.matches = matches;
    }

    public int getMaxMatchInDay() {
        return maxMatchInDay;
    }

    public void setMaxMatchInDay(int maxMatchInDay) {
        this.maxMatchInDay = maxMatchInDay;
    }

    public int getMinDayGapForTeam() {
        return minDayGapForTeam;
    }

    public void setMinDayGapForTeam(int minDayGapForTeam) {
        this.minDayGapForTeam = minDayGapForTeam;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public ArrayList<Match> getScheduleMatches() {
        return scheduleMatches;
    }

    public void setScheduleMatches(ArrayList<Match> scheduleMatches) {
        this.scheduleMatches = scheduleMatches;
    }

    private void addTeam(Team team)  // Hidden Method
    {
        if (this.tournamentTeams==null)
        {
            this.tournamentTeams = new HashSet<Team>();
        }
        this.tournamentTeams.add(team);
    }
    public void addTeam(String teamName,String city)  // Exposed Method
    {
        this.addTeam(new Team(teamName,city));
    }
}
