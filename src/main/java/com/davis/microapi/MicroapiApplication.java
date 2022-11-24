package com.davis.microapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.davis.microapi.model.Goal;
import com.davis.microapi.model.Post;
import com.davis.microapi.model.User;
import com.davis.microapi.repository.GoalRepository;
import com.davis.microapi.repository.PostRepository;
import com.davis.microapi.repository.UserRepository;

@SpringBootApplication
public class MicroapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroapiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PostRepository posts, UserRepository users, PasswordEncoder encoder, GoalRepository goals) {
		return (args) -> {

			User u1 = new User(
				"davis",
				encoder.encode("password"),
				"ROLE_USER"
			);
			users.save(u1);
			users.save(new User(
				"admin",
				encoder.encode("password"),
				"ROLE_USER,ROLE_ADMIN"
			));
			posts.save(new Post(
				"New Title",
				"new-post", 
				"Content",
				"author"
			));

			Goal g1 = new Goal(
				"UUID",
				"Goal2", 
				"verb", 
				null, 
				0, 
				1, 
				"null", 
				"null", 
				false, 
				"null", 
				false, 
				false);
			g1.setUser(u1);
			goals.save(g1);
				
		};
	}

}
