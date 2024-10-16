package dev.bispro.domain;

import dev.bispro.domain.exceptions.DataValidationException;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "a_account")
public class Account {

    @Id
    @Column(name = "a_accountId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @Column(name = "a_firstname", length = 50)
    private String firstname;

    @Column(name = "a_lastname", length = 50)
    private String lastname;

    @Column(name = "a_email", length = 50)
    private String email;

    @Column(name = "a_password", length = 50)
    private String password;


    public Account(Long accountId, String firstname, String lastname, String email, String password) {
        this.accountId = accountId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public Account() {

    }

    public Long getAccountId() {
        return accountId;
    }

    public void setFirstname(String firstname) {
        if (firstname == null || firstname.trim().isEmpty()) {
            throw DataValidationException.forInvalidInput("First name cannot be null or empty.");
        }
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        if (lastname == null || lastname.trim().isEmpty()) {
            throw DataValidationException.forInvalidInput("Last name cannot be null or empty.");
        }
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw DataValidationException.forInvalidInput("Invalid email format.");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw DataValidationException.forInvalidInput("Password must be at least 8 characters long.");
        }
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
