package com.davis.microapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.davis.microapi.model.User;


public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
    
}
