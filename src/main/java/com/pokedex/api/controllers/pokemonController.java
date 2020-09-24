package com.pokedex.api.controllers;

import com.pokedex.domain.models.Pokemon;
import com.pokedex.domain.services.PokemonService;
import com.pokedex.dto.PokemonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
        return ResponseEntity.ok(service.getPokemonById(id));
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
        PokemonDTO p = service.insert(pokemon);
        URI location = getUri(p.getId());
        return ResponseEntity.created(location).build();
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
        service.delete(id) ;
        return ResponseEntity.ok().build();
    }

}
