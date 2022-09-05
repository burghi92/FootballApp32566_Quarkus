package com.footballApp32566.data;

import com.footballApp32566.service.Matches;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class MatchRepository implements PanacheRepository<Matches> {


    /**
     * Methode um ein Match mit den Parametern des Heim und Auswärtsteams zu finden
     *
     * @param team1
     *                  Name des HeimTeams
     * @param team2
     *                  Name des Auswärtsteams
     * @return Optional<Matches>
     *                  Ein Optional aus Matches
     */
    public List<Matches> findMatchesByTeam1AndTeam2(String team1, String team2){

        Map<String, Object> params = new HashMap<>();
        params.put("team1", team1);
        params.put("team2", team2);

        return list("team1 = :team1 and team2 = :team2", params);
    }

}
