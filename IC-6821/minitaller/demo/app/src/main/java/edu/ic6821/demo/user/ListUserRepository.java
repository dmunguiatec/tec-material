package edu.ic6821.demo.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public final class ListUserRepository implements Repository {

    private final List<User> users = new ArrayList<>();


    @Override
    public Optional<User> create(User user) {
        final Optional<User> storedUser = read(user.getId());

        if (storedUser.isPresent()) {
            return Optional.empty();
        } else {
            this.users.add(user);
            return Optional.of(user);
        }
    }

    @Override
    public Optional<User> read(Long id) {
        return this.users.stream()
                .filter(storedUser -> storedUser.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<User> readAll() {
        return this.users;
    }

    @Override
    public Optional<User> update(User user) {
        for (int i = 0; i < this.users.size(); i++) {
            final User storedUser = this.users.get(i);
            if (user.getId().equals(storedUser.getId())) {
                this.users.set(i, user);
                return Optional.of(this.users.get(i));
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> delete(Long id) {
        for (int i = 0; i < this.users.size(); i++) {
            final User storedUser = this.users.get(i);
            if (id.equals(storedUser.getId())) {
                this.users.remove(i);
                return Optional.of(storedUser);
            }
        }

        return Optional.empty();
    }
}
