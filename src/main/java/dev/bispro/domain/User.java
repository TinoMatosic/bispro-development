package dev.bispro.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "u_users")
public class User {

    @Id
    @Column(name = "u_a_accountid", length = 100)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String accountId;

    @Column(name = "u_role", nullable = false, length = 100)
    private Role role;

    @Column(name = "u_plan", nullable = false, length = 100)
    private String plan;

    public User(String accountId, Role role, String plan) {
        this.accountId = accountId;
        this.role = role;
        this.plan = plan;
    }

    public User() {

    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
}
