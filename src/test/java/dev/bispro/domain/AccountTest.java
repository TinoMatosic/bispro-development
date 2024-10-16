package dev.bispro.domain;

import dev.bispro.domain.exceptions.DataValidationException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AccountTest {

    @Nested
    class Setters_Validation_Tests {

        private final Account account = new Account();

        @Test
        void valid_firstname_should_be_set() {
            account.setFirstname("John");
            assertThat(account.getFirstname()).isEqualTo("John");
        }

        @Test
        void invalid_firstname_should_throw_exception() {
            String msg = "First name cannot be null or empty";

            assertThatThrownBy(() -> account.setFirstname(null))
                    .isInstanceOf(DataValidationException.class)
                    .hasMessageContaining(msg);

            assertThatThrownBy(() -> account.setFirstname("   "))
                    .isInstanceOf(DataValidationException.class)
                    .hasMessageContaining(msg);
        }

        @Test
        void valid_lastname_should_be_set() {
            account.setLastname("Doe");
            assertThat(account.getLastname()).isEqualTo("Doe");
        }

        @Test
        void invalid_lastname_should_throw_exception() {
            String msg = "Last name cannot be null or empty";

            assertThatThrownBy(() -> account.setLastname(null))
                    .isInstanceOf(DataValidationException.class)
                    .hasMessageContaining(msg);

            assertThatThrownBy(() -> account.setLastname("   "))
                    .isInstanceOf(DataValidationException.class)
                    .hasMessageContaining(msg);
        }

        @Test
        void valid_email_should_be_set() {
            account.setEmail("john.doe@example.com");
            assertThat(account.getEmail()).isEqualTo("john.doe@example.com");
        }

        @Test
        void invalid_email_should_throw_exception() {
            String msg = "Invalid email format";

            assertThatThrownBy(() -> account.setEmail(null))
                    .isInstanceOf(DataValidationException.class)
                    .hasMessageContaining(msg);

            assertThatThrownBy(() -> account.setEmail("invalid-email"))
                    .isInstanceOf(DataValidationException.class)
                    .hasMessageContaining(msg);
        }

        @Test
        void valid_password_should_be_set() {
            account.setPassword("Password123");
            assertThat(account.getPassword()).isEqualTo("Password123");
        }

        @Test
        void invalid_password_should_throw_exception() {
            String msg = "Password must be at least 8 characters long";

            assertThatThrownBy(() -> account.setPassword(null))
                    .isInstanceOf(DataValidationException.class)
                    .hasMessageContaining(msg);

            assertThatThrownBy(() -> account.setPassword("short"))
                    .isInstanceOf(DataValidationException.class)
                    .hasMessageContaining(msg);
        }
    }
}
