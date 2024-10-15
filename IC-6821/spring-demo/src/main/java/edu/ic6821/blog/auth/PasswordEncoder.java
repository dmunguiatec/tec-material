package edu.ic6821.blog.auth;

public interface PasswordEncoder {
    String hash(String password);
    boolean check(String rawPassword, String hashedPassword);
}
