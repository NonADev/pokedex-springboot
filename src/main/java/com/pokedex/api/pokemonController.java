package com.pokedex.api;

import com.pokedex.domain.Pokemon;
import com.pokedex.domain.PokemonService;
import com.pokedex.dto.PokemonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pokemons")
public class pokemonController {
    @Autowired
    private PokemonService service;

    @GetMapping //@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PokemonDTO>> get() {
        return ResponseEntity.ok(service.getPokemons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDTO> getById(@PathVariable("id") Long id) {
        return service.getPokemonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/raw/{id}")
    public ResponseEntity<Pokemon> getRawPokemon(@PathVariable("id") Long id) {
        return service.getPokemonByIdRaw(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type1/{type1}")
    public ResponseEntity<List<PokemonDTO>> getByType1(@PathVariable("type1") String type) {
        List<PokemonDTO> pokemons = service.getPokemonsByType1(type);
        return pokemons.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pokemons);
    }

    @GetMapping("/type2/{type2}")
    public ResponseEntity<List<PokemonDTO>> getByType2(@PathVariable("type2") String type) {
        List<PokemonDTO> pokemons = service.getPokemonsByType2(type);
        return pokemons.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pokemons);
    }

    @GetMapping("/type1/{type1}/type2/{type2}")
    public ResponseEntity<List<PokemonDTO>> getByTypes(@PathVariable("type1") String type1, @PathVariable("type2") String type2) {
        List<PokemonDTO> pokemons = service.getPokemonsByTypes(type1, type2);
        return pokemons.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pokemons);
    }

    @PostMapping
    public ResponseEntity<Pokemon> post(@RequestBody Pokemon pokemon) {
        try {
            service.insert(pokemon);
            return ResponseEntity.created(getUri(pokemon.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> put(@RequestBody Pokemon pokemon, @PathVariable("id") Long id) {
        Pokemon pokemonAux = service.update(pokemon, id);
        return pokemonAux != null ? ResponseEntity.ok(pokemonAux) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
