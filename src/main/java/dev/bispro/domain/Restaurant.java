package dev.bispro.domain;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "r_restaurants")
public class Restaurant {

    @Id
    @Column(name = "r_restaurantId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "r_name", length = 50)
    private String name;

    @Column(name = "r_street", length = 50)
    private String street;

    @Column(name = "r_streetnumber", length = 10)
    private String number;

    @Column(name = "r_city", length = 20)
    private String city;

    @Column(name = "r_postalCode", length = 10)
    private String postalCode;

    @Column(name = "r_state", length = 50)
    private String state;

    @Column(name = "r_e_employees")
    @OneToMany
    private List<Employee> employees;

    public Restaurant(Long id, String name, String street, String number, String city, String postalCode, String state, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.number = number;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.employees = employees;
    }

    public Restaurant() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
