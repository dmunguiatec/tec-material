package edu.ic6821.blog.users;

import edu.ic6821.blog.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByExtId(String extId);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
