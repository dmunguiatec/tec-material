package edu.ic6821.demo.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    @NonNull private final Long id;
    @NonNull private final String username;
    @NonNull private final String password;
}

