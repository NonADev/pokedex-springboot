package com.pokedex.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pokemons tem unique IDS, mas não são gerados sequenciais
    private Long id;
    private String name;
    private String type1;
    private String type2;
    private int total;
    private int hp;
    private int attack;
    private int defense;
    private int sp_atk;
    private int sp_def;
    private int speed;
    private int generation;
    private boolean legendary;
}
