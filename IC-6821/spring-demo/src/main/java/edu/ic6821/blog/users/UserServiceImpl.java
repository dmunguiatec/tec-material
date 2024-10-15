package edu.ic6821.blog.users;

import edu.ic6821.blog.auth.PasswordEncoder;
import edu.ic6821.blog.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> signup(String username, String password, String name, String email) {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isPresent()) {
            return Optional.empty();
        }

        final User user = new User(username, this.encoder.hash(password), name, email);
        User userWithId = userRepository.save(user);
        System.out.println("userWithId = " + userWithId);

        return userRepository.findById(userWithId.getId());
    }
}
