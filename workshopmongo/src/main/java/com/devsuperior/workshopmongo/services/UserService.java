package com.devsuperior.workshopmongo.services;

import com.devsuperior.workshopmongo.models.dto.PostDTO;
import com.devsuperior.workshopmongo.models.dto.UserDTO;
import com.devsuperior.workshopmongo.models.entities.Post;
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
        User userEntity = getEntityById(id);
        return new UserDTO(userEntity);
    }

    public UserDTO insert(UserDTO userDTO) {
        User userEntity = new User();
        copyDtoToEntity(userDTO, userEntity);
        userEntity = userRepository.insert(userEntity);
        return new UserDTO(userEntity);
    }

    public UserDTO update(String id, UserDTO userDTO) {
        User userEntity = getEntityById(id);
        copyDtoToEntity(userDTO, userEntity);
        userEntity = userRepository.save(userEntity);
        return new UserDTO(userEntity);
    }

    public void delete (String id) {
        getEntityById(id);
        userRepository.deleteById(id);
    }

    private User getEntityById(String id) {
        try {
            Optional<User> result = userRepository.findById(id);
            return result.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
        } catch (Exception e) {

            throw e;
        }
    }

    public List<PostDTO> getUserPosts (String id) {
        User user = getEntityById(id);
        return user.getPosts().stream().map(x -> (new PostDTO(x))).toList();
    }

    private void copyDtoToEntity(UserDTO userDTO, User userEntity) {
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
    }
}

