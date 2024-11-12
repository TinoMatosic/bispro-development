package dev.bispro.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record PhoneNumber(String number) {

    public PhoneNumber {
        if (number == null || number.isEmpty()) throw PhoneNumberException.forNullValue();
        if(number.length() < 10) throw PhoneNumberException.forInvalidLength(number);
    }

    public boolean isEmpty() {
        return number.isEmpty(); // Employee Service (Zeile 119)
    }

    public static class PhoneNumberException extends RuntimeException {

        public PhoneNumberException(String message) {
            super(message);
        }

        static PhoneNumberException forNullValue() {
            String msg = "Phone number must not be null!";
            return new PhoneNumberException(msg);
        }

        static PhoneNumberException forInvalidLength(String number) {
            String msg = "Your phone number must be longer than 10 characters: %s; length: %d".formatted(number, number.length());
            return new PhoneNumberException(msg);
        }

    }
}
