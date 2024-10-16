package dev.bispro.domain;

import dev.bispro.persistence.converters.RoleConverter;
import jakarta.persistence.*;

@Entity
public class User extends Account {

    @Column(columnDefinition = RoleConverter.COLUMN_DEFINITION)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "u_plan", nullable = false)
    private Plan plan;

    @OneToOne
    @JoinColumn(name = "u_r_restaurant", nullable = false)
    private Restaurant restaurant;

    public User(Account account, Role role, Plan plan, Restaurant restaurant) {
        super(account.getAccountId(), account.getFirstname(), account.getLastname(), account.getEmail(), account.getPassword());
        this.role = role;
        this.plan = plan;
        this.restaurant = restaurant;
    }

    public User() {

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
