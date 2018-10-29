package com.tournament.project2.tournament.model;

import java.util.List;
import java.util.Objects;

public class Team
{

    private String teamName;
    private String teamCity;
    private List<Player> players;

    public Team(String name, String city)
    {
        this.teamName = name;
        this.teamCity = city;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(teamName, team.teamName) &&
                Objects.equals(teamCity, team.teamCity) &&
                Objects.equals(players, team.players);
    }

    @Override
    public int hashCode() {

        return Objects.hash(teamName, teamCity, players);
    }
}
