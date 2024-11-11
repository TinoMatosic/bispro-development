package dev.bispro.dtos;

public class EmployeeDTO {

    private Long id;
    private String name;
    private String phoneNumber;
    private double salary;

    public EmployeeDTO(Long id, String name, String phoneNumber, double salary) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
