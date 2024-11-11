package dev.bispro.services.impl;

import dev.bispro.domain.Employee;
import dev.bispro.dtos.EmployeeDTO;
import dev.bispro.persistence.EmployeeRepository;
import dev.bispro.services.EmployeeService;
import dev.bispro.services.exceptions.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO findByEmployeeId(Long employeeId) {
        validateEmployeeId(employeeId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> ServiceLayerException.notFound("Employee not found with ID: " + employeeId));
        return toEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            throw ServiceLayerException.forInvalidArgument("Employee cannot be null");
        }
        validateEmployeeDTO(employeeDTO);
        Employee employee = new Employee(
                employeeDTO.getName(),
                employeeDTO.getPhoneNumber(),
                employeeDTO.getSalary()
        );
        try {
            Employee savedEmployee = employeeRepository.save(employee);
            return toEmployeeDTO(savedEmployee);
        } catch (Exception e) {
            throw ServiceLayerException.forCreateError("Error creating Employee: " + e.getMessage());
        }
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        validateEmployeeId(employeeId);
        validateEmployeeDTO(employeeDTO);

        Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setName(employeeDTO.getName());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
            employee.setSalary(employeeDTO.getSalary());

            try {
                Employee updatedEmployee = employeeRepository.save(employee);
                return toEmployeeDTO(updatedEmployee);
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
        validateEmployeeId(employeeId);
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
    public List<EmployeeDTO> getAllEmployees() {
        try {
            return employeeRepository.findAll().stream()
                    .map(this::toEmployeeDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw ServiceLayerException.forGetError("Error retrieving all Employees: " + e.getMessage());
        }
    }

    private void validateEmployeeId(Long employeeId) {
        if (employeeId == null || employeeId < 0) {
            throw ServiceLayerException.forInvalidArgument("Invalid employee ID: " + employeeId);
        }
    }

    private void validateEmployeeDTO(EmployeeDTO employeeDTO) {
        if (employeeDTO.getSalary() < 0) {
            throw ServiceLayerException.forInvalidArgument("Salary cannot be negative");
        }
        if (employeeDTO.getName() == null || employeeDTO.getName().isEmpty()) {
            throw ServiceLayerException.forInvalidArgument("Employee name cannot be null or empty");
        }
        if (employee.getPhoneNumber() == null || employee.getPhoneNumber().isEmpty()) {
            throw ServiceLayerException.forInvalidArgument("Employee phone number cannot be null or empty");
        }
    }

    private EmployeeDTO toEmployeeDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getPhoneNumber(),
                employee.getSalary()
        );
    }
}
