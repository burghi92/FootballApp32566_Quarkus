package com.footballApp32566.data;

import com.footballApp32566.service.TeamStatistics;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeamStatsRepository implements PanacheRepository<TeamStatistics> {

    /**
     * Methode um die Statistik über einen Teamnamen aufzurufen
     *
     * @param name
     *                  Name des Teams
     * @return TeamStatistics
     *                  Die Teamstatistik
     */
    public TeamStatistics findTeamStatisticsByTeamName(String name){
        return find("teamName", name).firstResult();
    }
}
