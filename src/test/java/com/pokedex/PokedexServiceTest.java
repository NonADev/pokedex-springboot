package com.pokedex;

import com.pokedex.api.exception.ObjectNotFoundException;
import com.pokedex.api.models.Pokemon;
import com.pokedex.api.services.PokemonService;
import com.pokedex.api.dto.PokemonDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PokedexServiceTest {
    @Autowired
    private PokemonService service;

    @Test
    void PokemonInsertion() {
        Pokemon pokemonAux = new Pokemon();

        pokemonAux.setAttack(3);
        pokemonAux.setDefense(3);
        pokemonAux.setGeneration(1);
        pokemonAux.setHp(80);
        pokemonAux.setLegendary(false);
        pokemonAux.setName("monstruoso");
        pokemonAux.setSp_atk(3);
        pokemonAux.setSp_def(3);
        pokemonAux.setSpeed(3);
        pokemonAux.setTotal(525);
        pokemonAux.setType1("Grass");
        pokemonAux.setType2("Poison");

        PokemonDTO pokemonDto = service.insert(pokemonAux);

        assertNotNull(pokemonDto);

        Long id = pokemonDto.getId();
        assertNotNull(id);

        // Buscar objeto no banco
        Optional<Pokemon> pokemonRawOptional = service.getPokemonByIdRaw(id);
        assertTrue(pokemonRawOptional.isPresent());

        pokemonAux = pokemonRawOptional.get();
        assertEquals("monstruoso", pokemonAux.getName());
        assertEquals("Grass", pokemonAux.getType1());
        assertEquals("Poison", pokemonAux.getType2());

        // Deletar objeto no banco
        service.delete(id);

        // Garantir exclusão
        try{
            PokemonDTO pokemonDTO = service.getPokemonById(id);
            fail("O Pokemon não foi excluido");
        } catch (ObjectNotFoundException e) {
            // ok
        }
    }

    @Test
    void PokemonGetTest() {
        Optional<Pokemon> pokemon = service.getPokemonByIdRaw(1L);
        assertTrue(pokemon.isPresent());
        assertEquals("Bulbasaur", pokemon.get().getName());
    }

    @Test
    void PokemonListType1Test(){
        assertEquals(67, service.getPokemonsByType1("Grass").size());
        assertEquals(47, service.getPokemonsByType1("fire").size());
        assertEquals(24, service.getPokemonsByType1("Dragon").size());
    }

    @Test
    void PokemonListTest() {
        List<PokemonDTO> pokemons = service.getPokemons();
        assertEquals(722, pokemons.size());
    }
}
