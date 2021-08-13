package edu.tec.ic6821.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class User {
    private final int id;
    private final String name;
    private final String username;
    private final String email;
    private final Address address;
    private final String phone;
    private final String website;
    private final Company company;
}
