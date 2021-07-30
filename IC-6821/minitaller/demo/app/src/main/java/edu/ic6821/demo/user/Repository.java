package edu.ic6821.demo.user;

import java.util.Collection;
import java.util.Optional;

public interface Repository {
    Optional<User> create(User user);
    Optional<User> read(Long id);
    Collection<User> readAll();
    Optional<User> update(User user);
    Optional<User> delete(Long id);
}
