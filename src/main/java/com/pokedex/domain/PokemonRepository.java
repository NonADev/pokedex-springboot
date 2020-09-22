package com.pokedex.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
    List<Pokemon> findByType1(String type);

    List<Pokemon> findByType2(String type);

    List<Pokemon> findByType1AndType2(String type1, String type2);
}
