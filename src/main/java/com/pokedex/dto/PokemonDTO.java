package com.pokedex.dto;

import com.pokedex.domain.Pokemon;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

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