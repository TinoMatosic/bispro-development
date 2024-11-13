package dev.bispro.services;

import dev.bispro.domain.Employee;
import dev.bispro.dtos.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO findByEmployeeId(Employee.EmployeeId employeeId);

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(Employee.EmployeeId employeeId, EmployeeDTO employeeDTO);

    void deleteEmployee(Employee.EmployeeId employeeId);

    List<EmployeeDTO> getAllEmployees();
}
