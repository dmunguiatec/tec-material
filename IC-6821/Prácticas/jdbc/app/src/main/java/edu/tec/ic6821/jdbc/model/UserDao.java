package edu.tec.ic6821.jdbc.model;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> create(User user);
    List<User> findAll();
}
