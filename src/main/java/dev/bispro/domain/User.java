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
    private String email;

    @Column(name = "password", length = 50)
    private String password;

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

    public User(String firstname, String lastname, String email, String password, Role role, Plan plan, Restaurant restaurant) {
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
