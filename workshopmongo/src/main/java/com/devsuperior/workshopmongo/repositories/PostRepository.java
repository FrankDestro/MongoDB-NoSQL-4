package com.devsuperior.workshopmongo.repositories;

import com.devsuperior.workshopmongo.models.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    // Usando Query methods

    List<Post> findByTitleContainingIgnoreCase(String text);

}
