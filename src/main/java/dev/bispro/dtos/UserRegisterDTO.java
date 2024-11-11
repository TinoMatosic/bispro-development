package dev.bispro.dtos;

import dev.bispro.domain.Email;
import dev.bispro.domain.Plan;

public class UserRegisterDTO {

    private String firstName;

    private String lastName;

    private Email email;

    private String password;

    private Plan plan;

    public UserRegisterDTO(String firstName, String lastName, Email email, String password, Plan plan) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.plan = plan;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
