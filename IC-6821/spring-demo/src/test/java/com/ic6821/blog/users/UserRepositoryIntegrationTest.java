package com.ic6821.blog.users;

import com.ic6821.blog.users.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByExtId() {
        // given
        final User user1 = new User(
                "username|" + UUID.randomUUID(),
                "password1",
                "name1",
                "email1"
        );

        final User savedUser1 = userRepository.save(user1);

        final Optional<User> potentialRetrievedUser = userRepository.findById(savedUser1.getId());
        assertThat(potentialRetrievedUser).isPresent();
        final User retrievedUser = potentialRetrievedUser.get();

        // when
        final Optional<User> actual = userRepository.findByExtId(retrievedUser.getExtId());

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual.get()).isEqualTo(retrievedUser);
    }

    @Test
    public void findByExtIdNoResult() {
        // given
        final String nonExistingId = UUID.randomUUID().toString();

        // when
        final Optional<User> actual = userRepository.findByExtId(nonExistingId);

        // then
        assertThat(actual).isEmpty();
    }

    @Test
    public void findByEmail() {
        // given
        final User user1 = new User(
                "username|" + UUID.randomUUID(),
                "password1",
                "name1",
                "email1"
        );
        final User savedUser1 = userRepository.save(user1);

        final Optional<User> potentialRetrievedUser = userRepository.findById(savedUser1.getId());
        assertThat(potentialRetrievedUser).isPresent();
        final User retrievedUser = potentialRetrievedUser.get();

        // when
        final Optional<User> actual = userRepository.findByEmail(retrievedUser.getEmail());

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual.get()).isEqualTo(retrievedUser);
    }

    @Test
    public void findByEmailNoResult() {
        // given
        final String nonExistingId = UUID.randomUUID().toString();

        // when
        final Optional<User> actual = userRepository.findByEmail(nonExistingId);

        // then
        assertThat(actual).isEmpty();
    }

    @Test
    public void findByUsername() {
        // given
        final User user1 = new User(
                "username|" + UUID.randomUUID(),
                "password1",
                "name1",
                "email1"
        );
        final User savedUser1 = userRepository.save(user1);

        final Optional<User> potentialRetrievedUser = userRepository.findById(savedUser1.getId());
        assertThat(potentialRetrievedUser).isPresent();
        final User retrievedUser = potentialRetrievedUser.get();

        // when
        final Optional<User> actual = userRepository.findByUsername(retrievedUser.getUsername());

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual.get()).isEqualTo(retrievedUser);
    }

    @Test
    public void findByUsernameNoResult() {
        // given
        final String nonExistingId = UUID.randomUUID().toString();

        // when
        final Optional<User> actual = userRepository.findByUsername(nonExistingId);

        // then
        assertThat(actual).isEmpty();
    }

}
