package com.devsuperior.workshopmongo.services;

import com.devsuperior.workshopmongo.models.dto.PostDTO;
import com.devsuperior.workshopmongo.models.entities.Post;
import com.devsuperior.workshopmongo.repositories.PostRepository;
import com.devsuperior.workshopmongo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

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

    public List<PostDTO> findByTitle(String text) {
        List<Post> list = postRepository.searchTitle(text);
        return list.stream().map(x -> new PostDTO(x)).toList();
    }

    public List<PostDTO> fullSearch(String text, String start, String end) {
        Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
        Instant endMoment = convertMoment(end, Instant.now());

        List<Post> list = postRepository.fullSearch(text, startMoment, endMoment);
        return list.stream().map(x -> new PostDTO(x)).toList();
    }

    private Instant convertMoment(String priginalString, Instant alternative) {
        try {
            return Instant.parse(priginalString);
        } catch (DateTimeParseException e) {
            return alternative;
        }
    }
}


