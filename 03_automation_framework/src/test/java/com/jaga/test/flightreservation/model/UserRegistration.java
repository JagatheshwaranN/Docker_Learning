package com.jaga.test.flightreservation.model;

public record UserRegistration(
        String firstName,
        String lastName,
        String email,
        String password,
        Address address
) {
}
