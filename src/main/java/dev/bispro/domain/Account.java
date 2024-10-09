package dev.bispro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "a_account")
public class Account {

    @Id
    @Column(name = "a_accountid")
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

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
