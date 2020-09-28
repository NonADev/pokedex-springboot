package com.pokedex.api.controllers;

import com.pokedex.api.dto.UserDTO;
import com.pokedex.api.models.Role;
import com.pokedex.api.models.User;
import com.pokedex.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class userController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("/info")
    public ResponseEntity<?> userInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(UserDTO.create(user));
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> getByID(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> post(@RequestBody User user){
        UserDTO dto = service.insert(user);
        URI location = getUri(0L);
        return ResponseEntity.created(location).build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
