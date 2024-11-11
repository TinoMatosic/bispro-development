package dev.bispro.dtos;

import java.util.List;

public class RestaurantDTO {

    private Long id;
    private String name;
    private String street;
    private String number;
    private String city;
    private Integer postalCode;
    private String state;
    private List<EmployeeDTO> employees;
    private List<OrderDTO> orders;
    private List<LayerDTO> layers;

    // Constructor with all fields
    public RestaurantDTO(Long id, String name, String street, String number, String city, Integer postalCode, String state,
                         List<EmployeeDTO> employees, List<OrderDTO> orders, List<LayerDTO> layers) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.number = number;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.employees = employees;
        this.orders = orders;
        this.layers = layers;
    }

    // Getters and Setters
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

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public List<LayerDTO> getLayers() {
        return layers;
    }

    public void setLayers(List<LayerDTO> layers) {
        this.layers = layers;
    }
}
