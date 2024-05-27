package com.devsuperior.workshopmongo.models.dto;

import com.devsuperior.workshopmongo.models.entities.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserDTO {

    private String id;
    private String name;
    private String email;

    public UserDTO(User userEntity) {
        id = userEntity.getId();
        name = userEntity.getName();
        email = userEntity.getEmail();
    }
}
