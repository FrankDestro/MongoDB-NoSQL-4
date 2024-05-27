package com.devsuperior.workshopmongo.services;

import com.devsuperior.workshopmongo.models.dto.UserDTO;
import com.devsuperior.workshopmongo.models.entities.User;
import com.devsuperior.workshopmongo.repositories.UserRepository;
import com.devsuperior.workshopmongo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> list = userRepository.findAll();
        return list.stream().map(user -> new UserDTO(user)).toList();
    }

    public UserDTO findById(String id) {
        Optional<User> result = userRepository.findById(id);
        User entity = result.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
        return new UserDTO(entity);
    }

    public UserDTO insert(UserDTO userDTO) {
        User userEntity = new User();
        copyDtoToEntity(userDTO, userEntity);
        userEntity = userRepository.insert(userEntity);
        return new UserDTO(userEntity);
    }

    private void copyDtoToEntity(UserDTO userDTO, User userEntity) {
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
    }
}

