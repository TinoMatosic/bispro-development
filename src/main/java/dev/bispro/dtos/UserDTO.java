package dev.bispro.dtos;

import dev.bispro.domain.Email;
import dev.bispro.domain.PhoneNumber;
import dev.bispro.domain.Plan;
import dev.bispro.domain.Role;

public class UserDTO {

    private Long userId;

    private String firstName;

    private String lastName;

    private Email email;

    private PhoneNumber phoneNumber;

    private Role role;

    private Plan plan;

    public UserDTO(Long userId, String firstName, String lastName, Email email, Role role, Plan plan, PhoneNumber phoneNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.plan = plan;
        this.phoneNumber = phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
