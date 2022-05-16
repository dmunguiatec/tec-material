package edu.tec.ic6821.springjdbc;

import edu.tec.ic6821.springjdbc.model.User;
import edu.tec.ic6821.springjdbc.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {

	private UserRepository userRepository;

	@Autowired
	public App(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User("username1", "name1");
		User createdUser = userRepository.save(user);

		System.out.println("createdUser = " + createdUser);

		User user2 = new User("username2", "name2");
		User user3 = new User("username3", "name3");
		User user4 = new User("username4", "name4");
		userRepository.saveAll(List.of(user2, user3, user4));

		User retrievedUser = userRepository.findByUsername("username1");
		System.out.println("retrievedUser = " + retrievedUser);

		userRepository.findAll().forEach(System.out::println);

	}
}
