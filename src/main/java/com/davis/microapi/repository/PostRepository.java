package com.davis.microapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.davis.microapi.model.Post;


public interface PostRepository extends CrudRepository<Post, Long>{
    
}
