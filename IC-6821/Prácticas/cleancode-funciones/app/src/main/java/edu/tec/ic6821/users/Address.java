package edu.tec.ic6821.users;

public record Address(String street, String suite, String city, String zipcode, Geolocation geolocation) {

}
