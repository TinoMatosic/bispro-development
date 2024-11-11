package dev.bispro.services;

import dev.bispro.dtos.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO findByEmployeeId(Long employeeId);

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO);

    void deleteEmployee(Long employeeId);

    List<EmployeeDTO> getAllEmployees();
}
