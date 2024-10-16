package dev.bispro.domain;

import dev.bispro.domain.exceptions.DataValidationException;
import jakarta.persistence.*;

@Entity
public class Employee extends Account {

    @Column(name = "e_salary")
    private double salary;

    public Employee(Account account, double salary) {
        super(account.getAccountId(), account.getFirstname(), account.getLastname(), account.getEmail(), account.getPassword());
        this.salary = salary;
    }

    public Employee() {

    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if(salary < 0) throw DataValidationException.forInvalidInput("Salary can't be less than 0!");
        this.salary = salary;
    }
}
