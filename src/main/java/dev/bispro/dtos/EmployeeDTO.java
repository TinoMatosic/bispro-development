package dev.bispro.dtos;

import dev.bispro.domain.PhoneNumber;

public class EmployeeDTO {

    private Long employeeId;
    private String name;
    private PhoneNumber phoneNumber;
    private double salary;

    public EmployeeDTO(Long id, String name, PhoneNumber phoneNumber, double salary) {
        this.employeeId = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long id) {
        this.employeeId = id;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
