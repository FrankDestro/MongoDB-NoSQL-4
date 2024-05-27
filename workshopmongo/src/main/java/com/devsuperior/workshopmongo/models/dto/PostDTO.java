package com.devsuperior.workshopmongo.models.dto;

import com.devsuperior.workshopmongo.models.aggregate.Author;
import com.devsuperior.workshopmongo.models.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PostDTO {

    private String id;
    private Instant moment;
    private String title;
    private String body;
    private Author author;

    public PostDTO(Post postEntity) {
        id = postEntity.getId();
        moment = postEntity.getMoment();
        body = postEntity.getBody();
        title = postEntity.getTitle();
        author = postEntity.getAuthor();
    }
}
