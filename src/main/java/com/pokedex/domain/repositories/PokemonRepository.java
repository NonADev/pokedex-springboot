package com.pokedex.domain.repositories;

import com.pokedex.domain.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByType1(String type);

    List<Pokemon> findByType2(String type);

    List<Pokemon> findByType1AndType2(String type1, String type2);
}
