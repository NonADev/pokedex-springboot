package com.pokedex.api;

import com.pokedex.domain.Pokemon;
import com.pokedex.domain.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pokemons")
public class pokemonController {
    @Autowired
    private PokemonService service;

    @GetMapping //@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Pokemon>> get() {
        return ResponseEntity.ok(service.getPokemons());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Pokemon> pokemon = service.getPokemonById(id);
        return pokemon
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type1/{type1}")
    public ResponseEntity getByType1(@PathVariable("type1") String type) {
        List<Pokemon> pokemons = service.getPokemonsByType1(type);
        return pokemons.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pokemons);
    }

    @GetMapping("/type2/{type2}")
    public ResponseEntity getByType2(@PathVariable("type2") String type) {
        List<Pokemon> pokemons = service.getPokemonsByType2(type);
        return pokemons.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pokemons);
    }

    @GetMapping("/type1/{type1}/type2/{type2}")
    public ResponseEntity getByTypes(@PathVariable("type1") String type1, @PathVariable("type2") String type2) {
        List<Pokemon> pokemons = service.getPokemonsByTypes(type1, type2);
        return pokemons.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pokemons);
    }

    @PostMapping
    public Pokemon post(@RequestBody Pokemon pokemon) {
        return service.insert(pokemon);
    }

    @PutMapping("/{id}")
    public Pokemon put(@RequestBody Pokemon pokemon, @PathVariable("id") Long id) {
        return service.update(pokemon, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
