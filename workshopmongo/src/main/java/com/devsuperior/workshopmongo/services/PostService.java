package com.devsuperior.workshopmongo.services;

import com.devsuperior.workshopmongo.models.dto.PostDTO;
import com.devsuperior.workshopmongo.models.entities.Post;
import com.devsuperior.workshopmongo.repositories.PostRepository;
import com.devsuperior.workshopmongo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostDTO findById(String id) {
        Post postEntity = getEntityById(id);
        return new PostDTO(postEntity);
    }

    private Post getEntityById(String id) {
        Optional<Post> result = postRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    }
}


