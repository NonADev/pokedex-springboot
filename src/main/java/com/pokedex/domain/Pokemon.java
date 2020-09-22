package com.pokedex.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pokemon {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) //pokemons tem unique IDS, mas não são gerados sequenciais
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

    public Pokemon(Long id, String name, String type1, String type2, int total, int hp, int attack, int defense, int sp_atk, int sp_def, int speed, int generation, boolean legendary) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.total = total;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.sp_atk = sp_atk;
        this.sp_def = sp_def;
        this.speed = speed;
        this.generation = generation;
        this.legendary = legendary;
    }

    public Pokemon(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pokemon() {

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSp_atk() {
        return sp_atk;
    }

    public void setSp_atk(int sp_atk) {
        this.sp_atk = sp_atk;
    }

    public int getSp_def() {
        return sp_def;
    }

    public void setSp_def(int sp_def) {
        this.sp_def = sp_def;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public boolean isLegendary() {
        return legendary;
    }

    public void setLegendary(boolean legendary) {
        this.legendary = legendary;
    }
}
