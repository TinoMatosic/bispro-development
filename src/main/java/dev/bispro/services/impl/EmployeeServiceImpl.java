package dev.bispro.services.impl;

import dev.bispro.domain.Employee;
import dev.bispro.persistence.EmployeeRepository;
import dev.bispro.services.EmployeeService;
import dev.bispro.services.exceptions.ServiceLayerException;

public class EmployeeServiceImpl implements EmployeeService
{
    private  final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findByEmployeeId(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(()-> ServiceLayerException.notFound("Employee not found with ID: " + employeeId));
    }



}
