package com.tournament.project2.tournament;

import com.tournament.project2.tournament.model.Match;
import com.tournament.project2.tournament.model.Team;
import com.tournament.project2.tournament.model.Tournament;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RoundRobinFormat implements TournamentFormat
{
    public Queue<Match> createMatches(Tournament tournament)
    {
        Queue<Match> matches = new LinkedList<>();
        ArrayList<Team> teamsList = new ArrayList<Team>(tournament.getTournamentTeams());

        int totTeams = tournament.getTournamentTeams().size();
        for(int i = 0; i < totTeams; i++)
        {

            for(int j = 0; j < totTeams; j++)
            {
                if (i!=j) {
                    Team t1 = teamsList.get(i);
                    Team t2 = teamsList.get(j);
                    Match match = new Match(t1, t2, t1.getTeamCity());
                    matches.add(match);
                }
            }
        }
        return matches;
    }
}
