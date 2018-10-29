package com.tournament.project2.tournament;

import com.tournament.project2.tournament.model.Match;
import com.tournament.project2.tournament.model.Team;
import com.tournament.project2.tournament.model.Tournament;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class TournamentScheduler
{

    TournamentFormat format = new RoundRobinFormat();

    private  void createSchedule(Tournament tournament, Queue<Match> matches)
    {
        ArrayList<Match> scheduledMatches = new ArrayList<Match>();
        Date dt = tournament.getStartDate();
        while(matches.size()>0)
        {
            int matchCountInADay=0;
            int maxTraverse=matches.size();
            for (int i=0; i<=maxTraverse; i++) {
                Set occupiedTeams= getOccupiedTeams(dt,tournament);
                if (matches.size()>0)
                {
                    Match obj = matches.remove();
                    if (occupiedTeams.contains(obj.getFirstTeam()) || occupiedTeams.contains(obj.getSecondTeam())) {
                        matches.add(obj);
                    } else {
                        obj.setMatchNumber(scheduledMatches.size()+1);
                        //can call method hourAdjust() if want to schedule based on time
                        obj.setMatchDate(dt);
                        scheduledMatches.add(obj);
                        matchCountInADay++;
                        tournament.setScheduleMatches(scheduledMatches);
                        occupiedTeams.add(obj.getFirstTeam());
                        occupiedTeams.add(obj.getSecondTeam());
                        if(matchCountInADay==tournament.getMaxMatchInDay())
                        {
                            break;
                        }
                    }
                }
            }
            dt = dateAdjust(dt, 1);
        }
    }

    private Set<Team> getOccupiedTeams(Date dt, Tournament tournament) {
        ArrayList<Match> scheduledMatches = tournament.getScheduleMatches();
        int minDayDifference = tournament.getMinDayGapForTeam();
        Set<Team> teamList = new HashSet<Team>();
        for (int i=0; i<=minDayDifference;i++) {
            Date checkDate= dateAdjust(dt, -1*i);
            if (checkDate.after(tournament.getStartDate()) || checkDate.equals(tournament.getStartDate())) {
                List<Match> mList = getMatch(checkDate, scheduledMatches);
                if (mList != null || mList.size() > 0) {
                    for (Match m : mList) {
                        teamList.add(m.getFirstTeam());
                        teamList.add(m.getSecondTeam());
                    }
                }
            }
        }
        return teamList;
    }

    private List<Match> getMatch(Date dt, ArrayList<Match> scheduledMatches)
    {
        List<Match> mList = new ArrayList();
        if (scheduledMatches!=null)
        {
            for (Match m : scheduledMatches)
            {
                if (m.getMatchDate().equals(dt))
                {
                    mList.add(m);
                }
            }
        }
        return mList;
    }
    private Date dateAdjust(Date currentDate, int numberOfDaysToIncrement)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, numberOfDaysToIncrement);  // number of days to add

        return cal.getTime();
    }
    private Date hourAdjust(Date currentDate, int numberOfHourToIncrement)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.HOUR, numberOfHourToIncrement);  // number of hours to add

        return cal.getTime();
    }

    public ArrayList<Match> getMatchSchedule(Tournament tournament)
    {
        Queue<Match> matchList= format.createMatches(tournament);
        createSchedule(tournament, matchList);
        return tournament.getScheduleMatches();
    }
}
