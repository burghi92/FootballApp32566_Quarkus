package com.footballApp32566.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class TeamStatistics extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "rank_sequence",
            sequenceName = "rank_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rank_sequence"
    )
    public Long id;

    @NotNull
    @NotBlank
    public String teamName;

    @Min(0) @Max(17)
    public int games;

    @Min(0)
    public int goals;

    @Min(0)
    public int goalsAgainst;

    public int goalDifference;

    @Transient
    public int rank;

    @Min(0)
    public int points;

    public TeamStatistics() {
    }

    /**
     * Konstruktor für das Erzeugen einer TeamStatisitk
     *
     * @param team
     *                  Name des Teams
     * @param games
     *                  Anzahl Spiele des Teams
     * @param goals
     *                  Erzielte eigene Tore
     * @param goalsAgainst
     *                  Kassierte Gegentore
     * @param points
     *                  Gewonnene Punkte
     */
    public TeamStatistics(String team, int games, int goals, int goalsAgainst, int points) {
        this.teamName = team;
        this.games = games;
        this.goals = goals;
        this.goalDifference = goals-goalsAgainst;
        this.goalsAgainst = goalsAgainst;
        this.points = points;
    }

}
