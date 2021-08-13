package edu.tec.ic6821.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Company {
    private final String name;
    private final String catchPhrase;
    private final String bs;
}
