package com.pokedex;

import com.pokedex.api.PokedexApplication;
import com.pokedex.api.models.Pokemon;
import com.pokedex.api.dto.PokemonDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PokedexApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PokedexApiTest {
    @Autowired
    protected TestRestTemplate rest;

    public ResponseEntity<List<PokemonDTO>> getPokemons(String url) {
        return rest.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PokemonDTO>>() {
                }
        );
    }

    public ResponseEntity<PokemonDTO> getPokemon(String url) {
        return rest.getForEntity(url, PokemonDTO.class);
    }

    @Test
    public void PokemonTestGetAll() {
        List<PokemonDTO> pokemons = getPokemons("/api/v1/pokemons").getBody();
        assertNotNull(pokemons);
        assertEquals(722, pokemons.size());
    }

    @Test
    public void PokemonTestGetTypes() {
        assertEquals(67, Objects.requireNonNull(getPokemons("/api/v1/pokemons/type1/Grass").getBody()).size());
        assertEquals(47, Objects.requireNonNull(getPokemons("/api/v1/pokemons/type1/fire").getBody()).size());
        assertEquals(24, Objects.requireNonNull(getPokemons("/api/v1/pokemons/type1/Dragon").getBody()).size());

        assertEquals(HttpStatus.NO_CONTENT, getPokemons("/api/v1/pokemons/type1/xxx").getStatusCode());
    }

    @Test
    public void PokemonGetNonExistenteTest(){
        assertEquals(HttpStatus.NOT_FOUND, getPokemon("/api/v1/pokemons/2547").getStatusCode());
    }

    @Test
    public void PokemonSaveDeleteTest() {
        Pokemon pokemon = new Pokemon();
        pokemon.setType1("Grass");
        pokemon.setTotal(3);
        pokemon.setSpeed(3);
        pokemon.setSp_def(3);
        pokemon.setSp_atk(3);
        pokemon.setLegendary(false);
        pokemon.setName("reprocius");
        pokemon.setGeneration(1);
        pokemon.setHp(30);
        pokemon.setDefense(1);
        pokemon.setAttack(3);

        // insert
        ResponseEntity<PokemonDTO> response = rest.postForEntity("/api/v1/pokemons", pokemon, null);
        System.out.println(response);

        // verifica
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // busca
        String location = response.getHeaders().get("location").get(0);
        PokemonDTO pokemonDTO = getPokemon(location).getBody();

        assertNotNull(pokemonDTO);
        assertEquals("reprocius", pokemonDTO.getName());
        assertEquals(1, pokemonDTO.getGeneration());

        // delete
        rest.delete(location);

        //validação de delete
        assertEquals(HttpStatus.NOT_FOUND, getPokemon(location).getStatusCode());
    }
}
