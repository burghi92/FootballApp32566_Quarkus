package com.footballApp32566.api;

import com.footballApp32566.service.TeamStatistics;
import com.footballApp32566.service.TeamStatsService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * API-Klasse zum Auruf von Methoden zum Anzeigen der Statistik
 * API-Layer
 *
 * @author André Burkhart, 32566
 *
 */

@Path("quarkus/api/stats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class TeamStatisticsController {

    @Inject
    private TeamStatsService service;

    /**
     * Gibt ein Ranking des aktuellen Tabellenstandes wieder
     *
     * @return List<TeamStatistics>
     *                  Die geordnete Liste aller Mannschaften
     */
    @GET
    @Path("/rank")
    public List<TeamStatistics> getRanking(){

        return service.getRanking();
    }

    /**
     * Gibt eine ungeordnete Liste der Teamstatistiken zurück
     *
     * @return List<TeamStatistics>
     *                  Die ungeordnete Liste aller Mannschaften
     */
    @GET
    @Path("/all")
    public List<TeamStatistics> getAllStats(){

        return service.getAllStats();
    }

    /**
     * Gibt den Tabellenführerer zurück
     *
     * @return TeamStatistics
     *                  den Tabellenführer
     */
    @GET
    @Path("/first")
    public TeamStatistics getFirst(){

        return service.getBestTeam();
    }

    /**
     * Gibt den Tabellenletzten zurück
     *
     * @return TeamStatistics
     *                  den Tabellenletzten
     */
    @GET
    @Path("/last")
    public TeamStatistics getLast(){

        return service.getWorstTeam();
    }

    /**
     * Gibt die Statistik des Teams nach ID zurück
     *
     * @param id
     *                  ID des Teams
     *
     * @return TeamStatistics
     *                  Statistik des Teams
     */
    @GET
    @Path("/{id}")
    public TeamStatistics getStatsByID(@PathParam(value = "id") Long id){

        return service.getTeamStatsById(id);
    }
}
