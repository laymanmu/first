package com.example.springboot;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping("/")
	public String index() {
		User newUser = new User.Builder()
				.withName("user1")
				.withExperience(1)
				.withLevel(User.Level.Apprentice)
				.build();
		userRepository.save(newUser);

		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);

		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder();
		for (User user : users) {
			sb.append(gson.toJson(user)).append("<br>\n");
		}
		return sb.toString();
	}
}
