package com.tournament.project2.tournament.model;

import com.tournament.project2.util.Formatter;

import java.util.Date;

public class Match
{
    private Team firstTeam;
    private Team secondTeam;
    private Date matchDate;
    private String matchCity;
    private int matchNumber;

    public Match(Team t1, Team t2,String city)
    {
        this.firstTeam = t1;
        this.secondTeam = t2;
        this.matchCity = city;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchCity() {
        return matchCity;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public void setMatchCity(String matchCity) {
        this.matchCity = matchCity;
    }

    @Override
    public String toString() {
        return "Match No ["+Formatter.StringFormatterRightPad(String.valueOf(this.matchNumber),2)+"] [" + Formatter.StringFormatterRightPad(this.firstTeam.getTeamName()+ " V/S " + this.secondTeam.getTeamName(), 50) + "] \t[" + Formatter.DateFormatterWithTime(this.matchDate) + "] \tat [" + Formatter.StringFormatterLeftPad(this.matchCity,15) + "]";
    }

}