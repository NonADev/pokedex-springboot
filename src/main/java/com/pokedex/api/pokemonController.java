package com.pokedex.api;

import com.pokedex.domain.Pokemon;
import com.pokedex.domain.PokemonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class pokemonController {
    private PokemonService service = new PokemonService();

    @GetMapping() //@RequestMapping(method = RequestMethod.GET)
    public List<Pokemon> get() {
        return service.getPokemons();
    }

}
