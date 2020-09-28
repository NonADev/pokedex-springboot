package com.pokedex.api.dto;

import com.pokedex.api.models.Pokemon;
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