package com.pokedex.api.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class indexController {

    @GetMapping() //@RequestMapping(method = RequestMethod.GET)
    public String get() {
        return "Pokedex";
    }

    @GetMapping("/pokemon/{nome}")
    public String getPokemonByNome(@PathVariable("nome") String nome) {
        return "Pokemon por nome " + nome;
    }

    @GetMapping("/pokemon/tipo/{tipo}")
    public String getPokemonByTipo(@PathVariable("tipo") String tipo) {
        return "Lista de pokemon por tipo " + tipo;
    }


}
