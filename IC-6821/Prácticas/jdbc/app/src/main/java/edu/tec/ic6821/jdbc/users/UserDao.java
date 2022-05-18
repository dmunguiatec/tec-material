package edu.tec.ic6821.jdbc.users;

import edu.tec.ic6821.jdbc.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> create(User user);
    List<User> findAll();
}
