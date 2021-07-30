package edu.ic6821.demo.user;

import org.junit.Test;

import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListUserRepositoryTest {

    @Test
    public void testCreate() {
        // given
        User user = new User(17L, "someuser", "somepwd");

        ListUserRepository repository = new ListUserRepository();

        // when
        final Optional<User> result = repository.create(user);

        // then
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testCreatedDuplicatedId() {
        // given
        User user1 = new User(17L, "someuser", "somepwd");
        User user2 = new User(17L, "someuser2", "somepwd2");

        ListUserRepository repository = new ListUserRepository();
        repository.create(user1);

        // when
        final Optional<User> result = repository.create(user2);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    public void testReadExisting() {
        // given
        User user = new User(42L, "someuser", "somepwd");
        ListUserRepository repository = new ListUserRepository();
        repository.create(user);

        // when
        final Optional<User> result = repository.read(42L);

        // then
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testReadNotExisting() {
        // given
        ListUserRepository repository = new ListUserRepository();

        // when
        final Optional<User> result = repository.read(42L);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    public void testReadAllEmpty() {
        // given
        ListUserRepository repository = new ListUserRepository();

        // when
        final Collection<User> result = repository.readAll();

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    public void testReadAll() {
        // given
        User user1 = new User(17L, "someuser", "somepwd");
        User user2 = new User(18L, "someuser2", "somepwd2");

        ListUserRepository repository = new ListUserRepository();
        repository.create(user1);
        repository.create(user2);

        // when
        final Collection<User> result = repository.readAll();


        // then
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
    }

    @Test
    public void updateExisting() {
        // given
        User user1 = new User(17L, "someuser", "somepwd");
        User user2 = new User(18L, "someuser2", "somepwd2");
        User user3 = new User(23L, "someuser3", "somepwd3");
        User updatedUser = new User(18L, "updateduser", "updatedpwd");

        ListUserRepository repository = new ListUserRepository();
        repository.create(user1);
        repository.create(user2);
        repository.create(user3);

        // when
        final Optional<User> result = repository.update(updatedUser);

        // then
        assertTrue(result.isPresent());
        assertEquals(updatedUser, result.get());
    }

    @Test
    public void updateNotExisting() {
        // given
        User updatedUser = new User(18L, "updateduser", "updatedpwd");
        ListUserRepository repository = new ListUserRepository();

        // when
        final Optional<User> result = repository.update(updatedUser);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    public void deleteExisting() {
        // given
        User user1 = new User(17L, "someuser", "somepwd");
        User user2 = new User(18L, "someuser2", "somepwd2");
        User user3 = new User(23L, "someuser3", "somepwd3");

        ListUserRepository repository = new ListUserRepository();
        repository.create(user1);
        repository.create(user2);
        repository.create(user3);

        // when
        final Optional<User> result = repository.delete(18L);

        // then
        assertTrue(result.isPresent());
        assertEquals(user2, result.get());
    }

    @Test
    public void deleteNotExisting() {
        // given
        ListUserRepository repository = new ListUserRepository();

        // when
        final Optional<User> result = repository.delete(18L);

        // then
        assertTrue(result.isEmpty());
    }
}
