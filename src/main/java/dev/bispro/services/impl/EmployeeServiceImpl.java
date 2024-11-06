package dev.bispro.services.impl;

import dev.bispro.domain.Employee;
import dev.bispro.persistence.EmployeeRepository;
import dev.bispro.services.EmployeeService;
import dev.bispro.services.exceptions.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findByEmployeeId(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> ServiceLayerException.notFound("Employee not found with ID: " + employeeId));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employee == null) {
            throw ServiceLayerException.forInvalidArgument("Employee cannot be null");
        }
        validateEmployee(employee);
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            throw ServiceLayerException.forCreateError("Error creating Employee: " + e.getMessage());
        }
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee employee) {
        validateEmployee(employee);
        Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
        if (existingEmployee.isPresent()) {
            try {
                Employee updatedEmployee = updateEmployeeFields(employee, existingEmployee.get());
                return employeeRepository.save(updatedEmployee);
            } catch (Exception e) {
                throw ServiceLayerException.forUpdateError("Error updating Employee: " + e.getMessage());
            }
        } else {
            throw ServiceLayerException.notFound("Employee not found with ID: " + employeeId);
        }
    }

    private Employee updateEmployeeFields(Employee employee, Employee existingEmployee) {
        existingEmployee.setName(employee.getName());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setSalary(employee.getSalary());
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw ServiceLayerException.notFound("Employee not found with ID: " + employeeId);
        }
        try {
            employeeRepository.deleteById(employeeId);
        } catch (Exception e) {
            throw ServiceLayerException.forDeleteError("Error deleting Employee with ID [" + employeeId + "]: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw ServiceLayerException.forGetError("Error retrieving all Employees: " + e.getMessage());
        }
    }

    private void validateEmployee(Employee employee) {
        if (employee.getSalary() < 0) {
            throw ServiceLayerException.forInvalidArgument("Salary cannot be negative");
        }
        if (employee.getName() == null || employee.getName().isEmpty()) {
            throw ServiceLayerException.forInvalidArgument("Employee name cannot be null or empty");
        }
        if (employee.getPhoneNumber() == null || employee.getPhoneNumber().isEmpty()) {
            throw ServiceLayerException.forInvalidArgument("Employee phone number cannot be null or empty");
        }
    }
}
