package com.example.springboot;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
	private static Gson gson = new Gson();

	@Autowired
	UserRepository userRepository;

	@Autowired
	KafkaTemplate<String, String> kafka;

	@Value("${kafka.topic}")
	private String kafkaTopic;

	@RequestMapping("/")
	public String index() {
		return buildUserList();
	}

	@RequestMapping(method=RequestMethod.POST, value="/user")
	public String newUser(User user) {
		userRepository.save(user);
		String userJson = gson.toJson(user);
		kafka.send(kafkaTopic, userJson);
		return userJson;
	}

	private String buildUserList() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);

		StringBuilder sb = new StringBuilder();
		sb.append("<ol>\n");
		for (User user : users) {
			String userJson = gson.toJson(user);
			sb.append("<li>").append(userJson).append("</li>\n");
		}
		sb.append("</ol>\n");
		return sb.toString();
	}
}
