package com.footballApp32566.api;


import com.footballApp32566.service.MatchService;
import com.footballApp32566.service.Matches;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * API-Klasse zum Aufruf von Methoden zum Anlegen, Bearbeiten und Löschen von Matches
 * API-Layer
 *
 * @author André Burkhart, 32566
 *
 */

@Path("quarkus/api/matches")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class MatchController {

    @Inject //Führt die Dependency Injection aus
    private MatchService service;


    /**
     * Gibt eine Liste aller Matches zurück
     *
     * @return List<Matches>
     *                  Die Liste der Matches
     */
    @Path("")
    @GET
    public List<Matches> getAllMatches(){

        return service.getAllMatches();
    }

    /**
     * Gibt das Match mit der über den Pfad angegeben ID zurück
     * @param id: ID des Matches
     * @return match:
     *                  Das Match
     */
    @GET
    @Path("/{id}")
    public Matches getMatchByID(@PathParam(value = "id") Long id){
        Matches match = service.getMatchById(id);
        return match;
    }

    /**
     * Fügt ein neues Match zu den Daten hinzu
     *
     * @param match
     *                  Das Match
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void addNewMatch(Matches match){
        service.addNewMatch(match);
    }

    /**
     * Fügt eine Liste von Matches hinzu
     *
     * @param matches
     *                  Liste von Matches
     */
    @POST
    @Path("/matchday")
    public void addNewMatch(List<Matches> matches){

        service.addNewMatches(matches);
    }

    /**
     * Löscht ein Match auf Basis einer ID
     *
     * @param id
     *                  Die ID des Match
     */
    @Path("{id}")
    @DELETE()
    public void deleteMatchById(@PathParam(value = "id") Long id){

        service.deleteMatchbyId(id);
    }

    /**
     * Löscht ein Match auf Basis eines Matches
     *
     * @param match
     *                  Das zu löschende Match
     */
    @Path("")
    @DELETE
    public void deleteMatch(Matches match){

        service.deleteMatch(match);
    }

    /**
     * Aktualisiert die MatchGoals auf Basis der ID
     *
     * @param id
     *                  Die ID des matches
     * @param teamGoals1
     *                  Die neuen geschossenen Tore Team1
     * @param teamGoals2
     *                  Die neuen geschossenen Tore Team2
     */
    @Path("{id}")
    @PUT
    public void updateMatchGoals(@PathParam(value = "id") Long id,
                                 @QueryParam("teamGoals1") int teamGoals1,
                                 @QueryParam("teamGoals2") int teamGoals2){
        service.updateMatchGoals(id, teamGoals1, teamGoals2);
    }


}
