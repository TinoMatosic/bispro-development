package dev.bispro.domain;

import dev.bispro.domain.exceptions.DataValidationException;
import dev.bispro.persistence.converters.PlanConverter;
import dev.bispro.persistence.converters.RoleConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "email", length = 50)
    @Embedded
    private Email email;

    @Column(name = "phoneNumber", length = 50)
    @Embedded
    private PhoneNumber phoneNumber;

    @Column(name = "password", length = 50)
    @Embedded
    private Password password;

    @Column(columnDefinition = RoleConverter.COLUMN_DEFINITION)
    @Embedded
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = PlanConverter.COLUMN_DEFINITION)
    @Embedded
    @Enumerated(EnumType.STRING)
    private Plan plan;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_user_2_restaurant"))
    private Restaurant restaurant;

    public User(String firstname, String lastname, Email email, Password password, Role role, Plan plan, Restaurant restaurant) {
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
        setRole(role);
        setPlan(plan);
        this.restaurant = restaurant;
    }

    public User() {

    }

    public Long getUserId() {
        return userId;
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

    public void setEmail(Email email) {
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Password getPassword() {
        return password;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
