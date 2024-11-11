package dev.bispro.domain;


import dev.bispro.domain.exceptions.DataValidationException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @Column(name = "restaurantId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "street", length = 50)
    private String street;

    @Column(name = "streetnumber", length = 10)
    private String number;

    @Column(name = "city", length = 20)
    private String city;

    @Column(name = "postalCode", length = 10)
    private Integer postalCode;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "email", length = 50)
    @Embedded
    private Email email;

    @Column(name = "phoneNumber", length = 50)
    @Embedded
    private PhoneNumber phoneNumber;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_restaurant_2_employees"))
    private List<Employee> employees;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_restaurant_2_orders"))
    private List<Order> orders;

    @OneToMany(cascade = {CascadeType.ALL, CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_restaurant_2_layers"))
    private List<Layer> layers;


    public Restaurant(String name, String street, String number, String city, Integer postalCode, String state, Email email, PhoneNumber phoneNumber) {
        this.name = name;
        this.street = street;
        this.number = number;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.employees = Collections.emptyList();
        this.orders = Collections.emptyList();
        this.layers = Collections.emptyList();
    }

    public Restaurant() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw DataValidationException.forInvalidInput("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    /**
     * ^[A-Za-zÄÖÜäöüß\\s]+$: Erlaubt nur Buchstaben, Leerzeichen und Umlaute, aber keine Ziffern.
     */
    public void setStreet(String street) {
        if (street == null || street.trim().isEmpty()) {
            throw DataValidationException.forInvalidInput("Street cannot be null or empty.");
        }
        String streetRegex = "^[A-Za-zÄÖÜäöüß\\s]+$";
        if (!street.matches(streetRegex)) {
            throw DataValidationException.forInvalidInput("Street name must not contain numbers (e.g. 'Musterstraße').");
        }
        this.street = street;
    }



    public String getNumber() {
        return number;
    }


    /**
     * ^\\d+: Stellt sicher, dass die Nummer mit einer oder mehreren Ziffern beginnt.
     * [a-zA-Z]?$: Optionaler Buchstabe (ein einzelner Buchstabe am Ende, z.B. "a", "B").
     */
    public void setNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            throw DataValidationException.forInvalidInput("Number cannot be null or empty.");
        }
        String numberRegex = "^\\d+[a-zA-Z]?$";
        if (!number.matches(numberRegex)) {
            throw DataValidationException.forInvalidInput("Number must start with digits and may optionally have a letter (e.g. '14a' or '56').");
        }

        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (name == null || name.trim().isEmpty()) {
            throw DataValidationException.forInvalidInput("City cannot be null or empty.");
        }
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (name == null || name.trim().isEmpty()) {
            throw DataValidationException.forInvalidInput("State cannot be null or empty.");
        }
        this.state = state;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        if (employees == null) {
            throw DataValidationException.forInvalidInput("Employees list cannot be null.");
        }
        /* Überprüfen, ob die Liste null-Elemente enthält
        for (Employee employee : employees) {
            if (employee == null) {
                throw DataValidationException.forUpdateError("Employees list contains null elements.");
            }*/
        this.employees = employees;
        }


    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        if (postalCode <= 0) {
            throw DataValidationException.forInvalidInput("PostalCode cannot be under or equal 0.");
        }
        this.postalCode = postalCode;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        if (orders == null) {
            throw DataValidationException.forInvalidInput("Orders list cannot be null.");
        }

        this.orders = orders;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        if (layers == null) {
            throw DataValidationException.forInvalidInput("Layers cannot be nulla");
        }
        this.layers = layers;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
