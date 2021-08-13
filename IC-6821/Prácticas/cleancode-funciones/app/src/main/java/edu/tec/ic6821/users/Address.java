package edu.tec.ic6821.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Address {
    private final String street;
    private final String suite;
    private final String city;
    private final String zipcode;
    private final Geolocation geolocation;
}
