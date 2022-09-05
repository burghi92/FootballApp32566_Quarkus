package com.footballApp32566.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Geschäftsobjekt Match zum Anlegen eines Fussballspiels zwischen 2 Teams
 *
 * @author André Burkhart, 32566
 *
 */

@Entity
public class Matches extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "match_sequence",
            sequenceName = "match_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "match_sequence"
    )
    public Long id;

    @Column(nullable = false)
    public String team1;

    @Min(0)
    public int team1Goals;

    @NotNull
    @NotBlank
    public String team2;

    @Min(0)
    public int team2Goals;

    public Matches() {
    }

    /**
     * Konstruktor für ein Match inklusive seiner Tore
     *
     * @param team1
     *                  Name des Heimteams
     * @param team1Goals
     *                  Tore des Heimteams
     * @param team2
     *                  Name des Auswärtsteams
     * @param team2Goals
     *                  Tore des Auswärtsteams
     */
    public Matches(String team1, int team1Goals, String team2, int team2Goals) {
        this.team1 = team1;
        this.team1Goals = team1Goals;
        this.team2 = team2;
        this.team2Goals = team2Goals;
    }

    /**
     * Konstruktor für ein Match, dass 0:0 ausgegangen ist
     *
     * @param team1
     *                  Name des Heimteams
     * @param team2
     *                  Name des Auswärtsteams
     */
    public Matches(String team1, String team2) {
        this.team1 = team1;
        this.team1Goals = 0;
        this.team2 = team2;
        this.team2Goals = 0;
    }



}




