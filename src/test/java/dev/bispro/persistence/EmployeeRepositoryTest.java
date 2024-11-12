package dev.bispro.persistence;

import dev.bispro.domain.Employee;
import dev.bispro.domain.PhoneNumber;
import dev.bispro.domain.exceptions.DataValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        // Initialize an Employee for reuse in tests
        testEmployee = new Employee("Alice Johnson", new PhoneNumber("1234567890"), 50000);
    }

    @Test
    @DisplayName("Test saving an Employee")
    void testSaveEmployee() {
        // Using save method inherited from JpaRepository
        Employee savedEmployee = employeeRepository.save(testEmployee);
        assertNotNull(savedEmployee.getEmployeeId(), "Saved employee should have an ID");
        assertEquals("Alice Johnson", savedEmployee.getName(), "Employee name should match");
    }

    @Test
    @DisplayName("Test finding an Employee by ID")
    void testFindById() {
        Employee savedEmployee = employeeRepository.save(testEmployee);
        Employee foundEmployee = employeeRepository.findById(savedEmployee.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        assertEquals(savedEmployee.getEmployeeId(), foundEmployee.getEmployeeId(), "Employee IDs should match");
        assertEquals(savedEmployee.getName(), foundEmployee.getName(), "Employee names should match");
    }

    @Test
    @DisplayName("Test existsById with an existing Employee")
    void testExistsById() {
        Employee savedEmployee = employeeRepository.save(testEmployee);
        assertTrue(employeeRepository.existsById(savedEmployee.getEmployeeId()), "Employee should exist in the database");
    }

    @Test
    @DisplayName("Test deleting an Employee by ID")
    void testDeleteById() {
        Employee savedEmployee = employeeRepository.save(testEmployee);
        Long employeeId = savedEmployee.getEmployeeId();

        employeeRepository.deleteById(employeeId);
        assertFalse(employeeRepository.existsById(employeeId), "Employee should not exist after deletion");
    }

    @Test
    @DisplayName("Test finding non-existent Employee by ID")
    void testFindByIdNonExistent() {
        assertFalse(employeeRepository.findById(999L).isPresent(), "Non-existent employee ID should return empty Optional");
    }
}
