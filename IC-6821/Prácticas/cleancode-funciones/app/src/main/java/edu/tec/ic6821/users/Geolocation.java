package edu.tec.ic6821.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Geolocation {
    private final String latitude;
    private final String longitude;
}
