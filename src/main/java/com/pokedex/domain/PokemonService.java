package com.pokedex.domain;

import com.pokedex.dto.PokemonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    public List<PokemonDTO> getPokemons() {
        return repository.findAll().stream().map(PokemonDTO::create).collect(Collectors.toList());
    }

    public Optional<PokemonDTO> getPokemonById(Long id) {
        return repository.findById(id).map(PokemonDTO::create);
    }

    public Optional<Pokemon> getPokemonByIdRaw(Long id) {
        return repository.findById(id);
    }

    public List<PokemonDTO> getPokemonsByType1(String type) {
        return repository.findByType1(type).stream().map(PokemonDTO::create).collect(Collectors.toList());
    }

    public List<PokemonDTO> getPokemonsByType2(String type) {
        return repository.findByType2(type).stream().map(PokemonDTO::create).collect(Collectors.toList());
    }

    public List<PokemonDTO> getPokemonsByTypes(String type1, String type2) {
        return repository.findByType1AndType2(type1, type2).stream().map(PokemonDTO::create).collect(Collectors.toList());
    }

    public PokemonDTO insert(Pokemon pokemon) {
        return PokemonDTO.create(repository.save(pokemon));
    }

    public Pokemon update(Pokemon p, Long id) {
        Optional<Pokemon> optional = getPokemonByIdRaw(id);

        if (optional.isPresent()) {
            Pokemon aux = optional.get();
            aux.setAttack(p.getAttack());
            aux.setDefense(p.getDefense());
            aux.setGeneration(p.getGeneration());
            aux.setHp(p.getHp());
            aux.setLegendary(p.isLegendary());
            aux.setName(p.getName());
            aux.setSp_atk(p.getSp_atk());
            aux.setSp_def(p.getSp_def());
            aux.setSpeed(p.getSpeed());
            aux.setTotal(p.getTotal());
            aux.setType1(p.getType1());
            aux.setType2(p.getType2());

            repository.save(aux);
            return aux;
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Pokemon> p = getPokemonByIdRaw(id);

        if (p.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}