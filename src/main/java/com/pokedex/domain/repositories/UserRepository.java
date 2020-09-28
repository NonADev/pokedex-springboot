package com.pokedex.domain.repositories;

import com.pokedex.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    
    
}
