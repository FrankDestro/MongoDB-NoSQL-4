package com.devsuperior.workshopmongo.config;

import com.devsuperior.workshopmongo.models.entities.Post;
import com.devsuperior.workshopmongo.models.entities.User;
import com.devsuperior.workshopmongo.repositories.PostRepository;
import com.devsuperior.workshopmongo.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@PostConstruct
	public void init() {

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = User.builder()
				.name("Maria Brown")
				.email("maria@gmail.com")
				.build();

		User alex = User.builder()
				.name("Alex Green")
				.email("alex@gmail.com")
				.build();

		User bob = User.builder()
				.name("Bob Grey")
				.email("bob@gmail.com")
				.build();

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

	}
}
