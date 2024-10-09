package dev.bispro.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "e_employees")
public class Employee {

    @Id
    @Column(name = "e_a_accountid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @Column(name = "e_salary")
    private double salary;

    public Employee(Long accountId, double salary) {
        this.accountId = accountId;
        this.salary = salary;
    }

    public Employee() {

    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
