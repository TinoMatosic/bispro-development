package dev.bispro.services;

import dev.bispro.domain.Employee;

import java.util.List;

public interface EmployeeService
{
    Employee findByEmployeeId(Long employeeId);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long employeeId, Employee employee);

    void deleteEmployee(Long employeeId);

    List<Employee> getAllEmployees();
}
