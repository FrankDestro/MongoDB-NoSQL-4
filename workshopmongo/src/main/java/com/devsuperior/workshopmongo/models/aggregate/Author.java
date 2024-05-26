package com.devsuperior.workshopmongo.models.aggregate;


import com.devsuperior.workshopmongo.models.entities.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Author {

    private String id;
    private String name;

    public Author(User entityUser) {
        this.id = entityUser.getId();
        this.name = entityUser.getName();
    }
}
