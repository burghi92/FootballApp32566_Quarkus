package com.footballApp32566.service;


import com.footballApp32566.data.TeamStatsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Service-Klasse zum Auruf von Methoden zum Anlegen, Bearbeiten und Löschen von TeamStatistics
 * Business-Layer
 *
 * @author André Burkhart, 32566
 *
 */

@ApplicationScoped
public class TeamStatsService {

    @Inject
    TeamStatsRepository repository;

    /**
     * Gibt eine nach ihrem Erfolg geordnete Liste (Tabelle) aller Mannschaften zurück
     *
     * @return List<TeamStatistics>
     *                  Eine geordnete Liste aller TeamStatistiken
     */
    public List<TeamStatistics> getRanking(){
        TeamStatistics dummyTeam = new TeamStatistics("Dummy", 0, 0, 3000, 0);
        TeamStatistics selectedTeam = dummyTeam;

        List<TeamStatistics> teams = getAllStats();
        List<TeamStatistics> ranking = new ArrayList<>();
        int listsize = teams.size();
        for (int i = 0; i < listsize; i++) {

            for (TeamStatistics team : teams) {
                if (team.points > selectedTeam.points) {
                    selectedTeam = team;
                } else if (team.points == selectedTeam.points) {
                    if (team.goalDifference > selectedTeam.goalDifference) {
                        selectedTeam = team;
                    } else if (team.goalDifference == selectedTeam.goalDifference && team.goals > selectedTeam.goals)
                        selectedTeam = team;
                }
            }
            selectedTeam.rank = i+1;
            ranking.add(selectedTeam);
            teams.remove(selectedTeam);
            selectedTeam = dummyTeam;

        }
        return ranking;
    }

    /**
     * Gibt eine nicht geordnete Liste aller TeamStatistiken zurück
     *
     * @return List<TeamStatistics>
     *                  Eine ungeordnete Liste aller TeamStatistiken
     */
    public List<TeamStatistics> getAllStats() {

        return repository.listAll();
    }

    /**
     * Gibt eine TeamStatistk nach ihrer ID zurück
     *
     * @param id
     *                  ID des Teams
     *
     * @return TeamStatistics
     *                  Die Teamstatistik des Teams
     */
    public TeamStatistics getTeamStatsById(Long id) {
        if(null == repository.findById(id)){
            throw new IllegalStateException("Match with id "+ id +" could not be found");
        }

        return repository.findById(id);
    }

    /**
     * Gibt das beste aller Teams zurück
     *
     * @return TeamStatistics
     *                  Eine TeamStatistik für das beste Team
     */
    public TeamStatistics getBestTeam() {
        List<TeamStatistics> teamsRanked = getRanking();
        return teamsRanked.get(0);
    }

    /**
     * Gibt das schlechteste aller Teams zurück
     *
     * @return TeamStatistics
     *                  Eine TeamStatistik für das schlechteste Team
     */
    public TeamStatistics getWorstTeam() {
        List<TeamStatistics> teamsRanked = getRanking();
        return teamsRanked.get(teamsRanked.size()-1);
    }

    /**
     * Erzeugt oder Aktualisiert die TeamStatistik um ein weiteres Match
     *
     * @param match
     *                  Eine Match, dass der Teamstatistik hinzugefügt werden soll
     */
    public void addMatchStatistic(Matches match){
        String team1 = match.team1;
        String team2 = match.team2;
        TeamStatistics statsTeam1 = getTeamByName(team1);
        TeamStatistics statsTeam2 = getTeamByName(team2);


        int[] points = calculateMatchpoints(match);

        if(statsTeam1==null){
            statsTeam1 = new TeamStatistics(team1, 1, match.team1Goals, match.team2Goals, points[0]);
        }else{
            statsTeam1 = updateTeamStats(statsTeam1, match.team1Goals, match.team2Goals, points[0]);
            statsTeam1.games = statsTeam1.games +1;
        }


        if(statsTeam2==null){
            statsTeam2 = new TeamStatistics(team2, 1, match.team2Goals, match.team1Goals, points[1]);

        }else{
            statsTeam2 = updateTeamStats(statsTeam2, match.team2Goals, match.team1Goals, points[1]);
            statsTeam2.games = statsTeam2.games +1;

        }
        repository.persist(statsTeam1);
        repository.persist(statsTeam2);

    }

    /**
     * Entfernt eine MatchStatistic
     *
     * @param match
     *                  Eine Match, dass aus der Teamstatistik wieder entfernt werden soll
     */

    public void removeMatchStatistic(Matches match){
        String team1 = match.team1;
        String team2 = match.team2;
        TeamStatistics statsTeam1 = getTeamByName(team1);
        TeamStatistics statsTeam2 = getTeamByName(team2);

        int[] points = calculateMatchpoints(match);

        statsTeam1 = updateTeamStats(statsTeam1, -match.team1Goals, -match.team2Goals, -points[0]);
        statsTeam1.games = statsTeam1.games -1;
        statsTeam2 = updateTeamStats(statsTeam2, -match.team2Goals, -match.team1Goals, -points[1]);
        statsTeam2.games = statsTeam2.games -1;
        repository.persist(statsTeam1);
        repository.persist(statsTeam2);

    }

    //Private Methods for Internal Use only

    private TeamStatistics updateTeamStats(TeamStatistics team, int goalsInMatch, int goalsAgainstInMatch, int points) {
        team.goalsAgainst = team.goalsAgainst + goalsAgainstInMatch;
        team.goals = team.goals + goalsInMatch;
        team.points = team.points + points;
        team.goalDifference = team.goals - team.goalsAgainst;
        return team;
    }

    private TeamStatistics getTeamByName(String name){
        TeamStatistics stats = repository.findTeamStatisticsByTeamName(name);

        if(stats == null){
            return null;
        }
        return stats;
    }

    private int[] calculateMatchpoints(Matches match){

        int[] points = new int[2];
        int pointsTeam1 = 0;
        int pointsTeam2 = 0;
        if(match.team1Goals>match.team2Goals){
            pointsTeam1 = 3;
        }else if(match.team1Goals== match.team2Goals){
            pointsTeam1 = 1;
            pointsTeam2 = 1;
        }else{
            pointsTeam2 = 3;
        }
        points[0]=pointsTeam1;
        points[1]=pointsTeam2;

        return points;

    }
}
