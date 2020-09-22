package com.pokedex.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    public Iterable<Pokemon> getPokemons() {
        return repository.findAll();
    }

    public Optional<Pokemon> getPokemonById(Long id) {
        return repository.findById(id);
    }

    public List<Pokemon> getPokemonsByType1(String type) {
        return repository.findByType1(type);
    }

    public List<Pokemon> getPokemonsByType2(String type) {
        return repository.findByType2(type);
    }

    public List<Pokemon> getPokemonsByTypes(String type1, String type2){
        return repository.findByType1AndType2(type1, type2);
    }

    public Pokemon insert(Pokemon pokemon) {
        return repository.save(pokemon);
    }

    public Pokemon update(Pokemon p, Long id) {
        Optional<Pokemon> optional = getPokemonById(id);

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
            throw new RuntimeException("Não foi possivel atualizar o registro");
        }
    }

    public void delete(Long id) {
        Optional<Pokemon> p = getPokemonById(id);

        if (p.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Registro não encontrado");
        }
    }
}