package dev.bispro.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String password) {

    private static final int MIN_LENGTH = 8;
    private static final String REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public Password {
        if (password == null) throw PasswordException.forNullValue();
        if (password.length() < MIN_LENGTH) throw PasswordException.forTooShort(password);
        if (!password.matches(REGEX)) throw PasswordException.forWeakPassword(password);
    }

    public static class PasswordException extends RuntimeException {

        public PasswordException(String message) {
            super(message);
        }

        static PasswordException forNullValue() {
            return new PasswordException("Password must not be null.");
        }

        static PasswordException forTooShort(String value) {
            return new PasswordException("Password must be at least %d characters long. Provided: %d characters.".formatted(MIN_LENGTH, value.length()));
        }

        static PasswordException forWeakPassword(String value) {
            String message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.";
            return new PasswordException(message);
        }
    }
}
