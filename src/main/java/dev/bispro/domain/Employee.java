package dev.bispro.domain;

import dev.bispro.domain.exceptions.DataValidationException;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee{

    @Id
    @Column(name = "employeeId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "phoneNumber", length = 50)
    @Embedded
    private PhoneNumber phoneNumber;

    @Column(name = "salary")
    private double salary;

    public Employee(String name, PhoneNumber phoneNumber, double salary) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        setSalary(salary);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
