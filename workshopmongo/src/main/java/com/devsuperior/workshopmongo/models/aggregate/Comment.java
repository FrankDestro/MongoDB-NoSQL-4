package com.devsuperior.workshopmongo.models.aggregate;

import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Comment {

    private String text;
    private Instant moment;

    private Author author;

}
