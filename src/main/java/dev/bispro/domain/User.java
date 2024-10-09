package dev.bispro.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "u_users")
public class User {

    @Id
    @Column(name = "u_a_accountid", length = 100)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @Column(name = "u_role", nullable = false, length = 100)
    private Role role;

    @Column(name = "u_plan", nullable = false, length = 100)
    private String plan;

    @OneToOne
    @JoinColumn(name = "u_r_restaurant")
    private Restaurant restaurant;

    public User(Long accountId, Role role, String plan, Restaurant restaurant) {
        this.accountId = accountId;
        this.role = role;
        this.plan = plan;
        this.restaurant = restaurant;
    }

    public User() {

    }

    public Long getAccountId() {
        return accountId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
