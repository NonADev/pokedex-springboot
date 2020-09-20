package com.pokedex.domain;

import java.util.ArrayList;
import java.util.List;

public class PokemonService {
    public List<Pokemon> getPokemons(){
        List<Pokemon> pokemons = new ArrayList<>();

        pokemons.add(new Pokemon((long) 15, "pikachu"));
        pokemons.add(new Pokemon((long) 7, "meaw"));
        pokemons.add(new Pokemon((long) 11, "charizard"));

        return pokemons;
    }
}
