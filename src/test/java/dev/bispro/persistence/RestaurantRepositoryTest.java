package dev.bispro.persistence;

import dev.bispro.domain.Restaurant;
import dev.bispro.domain.Employee;
import dev.bispro.domain.PhoneNumber;
import dev.bispro.domain.Email;
import dev.bispro.domain.exceptions.DataValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    private Restaurant testRestaurant;

    @BeforeEach
    void setUp() {
        testRestaurant = new Restaurant(
                "Testaurant",
                "Main Street",
                "123A",
                "Test City",
                12345,
                "Test State",
                new Email("testaurant@example.com"),
                new PhoneNumber("1234567890")
        );
    }

    @Test
    @DisplayName("Test saving a Restaurant")
    void testSaveRestaurant() {
        Restaurant savedRestaurant = restaurantRepository.save(testRestaurant);
        assertNotNull(savedRestaurant.getId(), "Saved restaurant should have an ID");
        assertEquals("Testaurant", savedRestaurant.getName(), "Restaurant name should match");
    }

    @Test
    @DisplayName("Test finding a Restaurant by ID")
    void testFindById() {
        Restaurant savedRestaurant = restaurantRepository.save(testRestaurant);
        Restaurant foundRestaurant = restaurantRepository.findById(savedRestaurant.getId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        assertEquals(savedRestaurant.getId(), foundRestaurant.getId(), "IDs should match");
        assertEquals(savedRestaurant.getName(), foundRestaurant.getName(), "Names should match");
    }

    @Test
    @DisplayName("Test adding employees to a Restaurant")
    void testAddEmployees() {
        Employee employee1 = new Employee("John Doe", new PhoneNumber("9876543210"), 3500.0);
        Employee employee2 = new Employee("Jane Smith", new PhoneNumber("1234567890"), 4000.0);

        testRestaurant.setEmployees(List.of(employee1, employee2));
        Restaurant savedRestaurant = restaurantRepository.save(testRestaurant);

        assertEquals(2, savedRestaurant.getEmployees().size(), "Restaurant should have two employees");
        assertEquals("John Doe", savedRestaurant.getEmployees().get(0).getName(), "First employee name should match");
        assertEquals(3500.0, savedRestaurant.getEmployees().get(0).getSalary(), "First employee salary should match");
    }

    @Test
    @DisplayName("Test validation: invalid employee salary")
    void testInvalidEmployeeSalary() {
        // Assert that the exception is thrown during the creation of the Employee object
        assertThrows(DataValidationException.class,
                () -> new Employee("Invalid Employee", new PhoneNumber("1234567890"), -100.0),  // Negative salary
                "Salary can't be less than 0!"
        );
    }


    @Test
    @DisplayName("Test validation: invalid employee phone number")
    void testInvalidEmployeePhoneNumber() {
        // Assert that the exception is thrown during the creation of the PhoneNumber object
        assertThrows(PhoneNumber.PhoneNumberException.class,
                () -> new PhoneNumber("123"),  // Invalid phone number (too short)
                "Your phone number must be longer than 10 characters: 123; length: 3"
        );
    }


    @Test
    @DisplayName("Test validation: null employee name")
    void testNullEmployeeName() {
        // Assert that the exception is thrown during the creation of the Employee object
        assertThrows(DataValidationException.class,
                () -> new Employee(null, new PhoneNumber("9876543210"), 3000.0),  // Create Employee with null name
                "Employee name cannot be null or empty."
        );
    }


    @Test
    @DisplayName("Test validation: invalid email format should throw exception")
    void testInvalidEmail() {
        // The exception is expected to be thrown during the creation of Email object
        assertThrows(Email.EmailException.class,
                () -> new Email("invalid-email"),  // Invalid email format
                "Email (invalid-email) must be a string according to RFC2822");
    }


    @Test
    @DisplayName("Test validation: null email should throw exception")
    void testNullEmail() {
        assertThrows(Email.EmailException.class, () -> new Email(null), "Null email value should throw exception");
    }

    @Test
    @DisplayName("Test setting invalid phone number should throw exception")
    void testInvalidPhoneNumber() {
        // The exception is expected to be thrown during the creation of PhoneNumber
        assertThrows(PhoneNumber.PhoneNumberException.class,
                () -> new PhoneNumber("123"),  // Invalid phone number
                "Your phone number must be longer than 10 characters: 123; length: 3");
    }


    @Test
    @DisplayName("Test validation: invalid street name should throw exception")
    void testInvalidStreetName() {
        // The exception is expected to be thrown during the call to setStreet
        assertThrows(DataValidationException.class,
                () -> testRestaurant.setStreet("123 Main"),
                "Street name must not contain numbers (e.g. 'Musterstraße').");
    }


    @Test
    @DisplayName("Test validation: invalid street number format should throw exception")
    void testInvalidStreetNumber() {
        // The exception is expected to be thrown during the call to setNumber
        assertThrows(DataValidationException.class,
                () -> testRestaurant.setNumber("123!!"),
                "Invalid street number format should throw exception");
    }

}
