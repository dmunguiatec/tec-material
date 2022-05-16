package edu.tec.ic6821.springjdbc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String name;
    private LocalDateTime createdOn = LocalDateTime.now();
    private LocalDateTime lastUpdatedOn = LocalDateTime.now();

    public User() {
    }

    public User(String username, String name) {
        this.username = username;
        this.name = name;
        this.id = null;
        this.createdOn = LocalDateTime.now();
        this.lastUpdatedOn = LocalDateTime.now();
    }

    public User(Long id,
                String username,
                String name,
                LocalDateTime createdOn,
                LocalDateTime lastUpdatedOn) {
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
        User user = (User) o;
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
