package edu.tec.ic6821.jdbc.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    
    private final Long id;
    private final String username;
    private final String name;
    private final LocalDateTime createdOn;
    private final LocalDateTime lastUpdatedOn;

    public User(final String username, final String name) {
        this.username = username;
        this.name = name;
        this.id = null;
        this.createdOn = null;
        this.lastUpdatedOn = null;
    }

    public User(final Long id,
                final String username,
                final String name,
                final LocalDateTime createdOn,
                final LocalDateTime lastUpdatedOn) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.createdOn = createdOn;
        this.lastUpdatedOn = lastUpdatedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(name, user.name) && Objects.equals(createdOn, user.createdOn) && Objects.equals(lastUpdatedOn, user.lastUpdatedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, createdOn, lastUpdatedOn);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, username='%s', name='%s', createdOn=%s, lastUpdatedOn=%s}",
                id,
                username,
                name,
                createdOn,
                lastUpdatedOn);
    }
}
