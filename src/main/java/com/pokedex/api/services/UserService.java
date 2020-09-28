package com.pokedex.api.services;

import com.pokedex.api.dto.UserDTO;
import com.pokedex.api.exception.ObjectNotFoundException;
import com.pokedex.api.models.User;
import com.pokedex.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserDTO getUserById(Long id){
        return repository.findById(id).map(UserDTO::create).orElseThrow(()->new ObjectNotFoundException("User not found"));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public UserDTO insert(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setSenha(encoder.encode(user.getSenha()));
        return UserDTO.create(repository.save(user));
    }

    public List<UserDTO> getUsers(){
        return repository.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
    }
}
