package com.pokedex.domain.dto;

import com.pokedex.domain.models.Pokemon;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class PokemonDTO {
    private Long id;
    private String name;
    private int generation;
    private boolean legendary;

    public static PokemonDTO create(Pokemon pokemon){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pokemon, PokemonDTO.class);
    }
}