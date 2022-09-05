package com.footballApp32566.service;

import com.footballApp32566.data.MatchRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Service-Klasse zum Auruf von Methoden zum Anlegen, Bearbeiten und Löschen von Matches
 * Business-Layer
 *
 * @author André Burkhart, 32566
 *
 */

@ApplicationScoped
public class MatchService {

    @Inject
    MatchRepository repository;
    @Inject
    TeamStatsService stats;

    /**
     * Gibt eine Liste von Matches zurück
     *
     * @return List<Matches>
     *                  Eine Liste aller Matches
     */
    public List<Matches> getAllMatches() {
        return repository.listAll();
    }

    /**
     * Gibt ein einzelnes Match zurück
     *
     * @param id
     *                  Die ID des Matches
     * @return Matches
     *                  Ein einzelnes Match
     */
    public Matches getMatchById(Long id) {
        if(null == repository.findById(id)){
            throw new IllegalStateException("Match with id "+ id +" could not be found");
        }
        Matches byId = repository.findById(id);
        return byId;
    }

    /**
     * Fügt ein Match dem Repository hinzu und Aktualisiert die TeamStats
     *
     * @param match
     *                  Das Match
     */
    public void addNewMatch(Matches match){
        if (matchInDatabase(match)){
            throw new IllegalStateException("Match already included");
        }

        stats.addMatchStatistic(match);

        repository.persist(match);
    }

    /**
     * Fügt eine Liste von Matches dem Repository hinzu und Aktualisiert die TeamStats
     *
     * @param matches
     *                  Die Liste der matches
     */
    public void addNewMatches(List<Matches> matches){
        for (Matches match : matches) {
            addNewMatch(match);
        }
    }

    /**
     * Löscht ein Match wieder aus dem Repository und korrigiert auch die TeamStats wieder
     *
     * @param match
     *                  Das Match
     */
    public void deleteMatch(Matches match){

        if(!matchInDatabase(match)){
            throw new IllegalStateException("Match could not be found");
        }

        stats.removeMatchStatistic(match);
        repository.delete(match);

    }

    /**
     * Löscht ein Match wieder aus dem Repository und korrigiert auch die TeamStats wieder
     *
     * @param id
     *                  Die ID des Match
     */
    public void deleteMatchbyId(Long id){

        if(null == repository.findById(id)){
            throw new IllegalStateException("Match with id "+ id +" could not be found");
        }
        stats.removeMatchStatistic(getMatchById(id));

        repository.deleteById(id);

    }


    /**
     * Aktualisiert ein Match bezüglich seiner Tore und korrigiert auch die TeamStats
     *
     * @param matchid
     *                  Die ID des Match
     * @param teamGoals1
     *                  Die neuen teamGoals des Heimteams
     * @param teamGoals2
     *                  Die neuen teamGoals des Auswärtsteams
     */

    public void updateMatchGoals(Long matchid, int teamGoals1, int teamGoals2){
        Matches match = repository.findById(matchid);

        stats.removeMatchStatistic(match);

        if(match.equals(null)){
            throw new IllegalStateException("Match with id "+ matchid +" could not be found");
        }

        if(teamGoals1<0||teamGoals2<0){
            throw new IllegalStateException("Team Goals can not be negativ");
        }
        if(teamGoals1!=match.team1Goals){
            match.team1Goals = teamGoals1;
        }

        if(teamGoals2!=match.team2Goals){
            match.team2Goals = teamGoals2;
        }

        stats.addMatchStatistic(match);

    }


    private boolean matchInDatabase(Matches match){
        List<Matches> matchInDatabase = repository.findMatchesByTeam1AndTeam2(match.team1, match.team2);
        return !matchInDatabase.isEmpty();
    }


}
