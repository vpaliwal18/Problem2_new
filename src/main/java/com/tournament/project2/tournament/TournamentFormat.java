package com.tournament.project2.tournament;

import com.tournament.project2.tournament.model.Match;
import com.tournament.project2.tournament.model.Tournament;

import java.util.Queue;

public interface TournamentFormat
{
    public Queue<Match> createMatches(Tournament tournament);
}
