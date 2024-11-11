package dev.bispro.domain;

import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public record Email(String value) {

    public static final String REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public Email{
        if (value == null) throw EmailException.forNullValue();
        if (!PATTERN.matcher(value).matches()) throw EmailException.forInvalidValue(value);
    }

    public static class EmailException extends RuntimeException {

        public EmailException(String message) {
            super(message);
        }

        static Email.EmailException forNullValue() {
            String msg = "Email value must not be null";
            return new Email.EmailException(msg);
        }

        static Email.EmailException forInvalidValue(String value) {
            String msg = "Email (%s) must be a string according to RFC2822: %s".formatted(value, REGEX);
            return new Email.EmailException(msg);
        }

    }
}
