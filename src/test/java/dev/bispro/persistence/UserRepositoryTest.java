package dev.bispro.persistence;

import dev.bispro.domain.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User("Tino", "Matosic", new Email("tino@gmail.com"), new Password("StrongPass1@"), Role.USER, Plan.PRO,
                null
        );
    }

    @Test
    @DisplayName("Test saving a User")
    void testSaveUser() {
        User savedUser = userRepository.save(testUser);

        assertNotNull(savedUser.getUserId(), "Saved user should have an ID");
        assertEquals("Tino", savedUser.getFirstname(), "User's first name should match");
        assertEquals("tino@gmail.com", savedUser.getEmail().email(), "User's email should match");
    }

    @Test
    @DisplayName("Test finding a User by ID")
    void testFindById() {
        User savedUser = userRepository.save(testUser);
        User foundUser = userRepository.findById(savedUser.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        assertEquals(savedUser.getUserId(), foundUser.getUserId(), "User IDs should match");
        assertEquals(savedUser.getFirstname(), foundUser.getFirstname(), "User first names should match");
    }

    @Test
    @DisplayName("Test existsById with an existing User")
    void testExistsById() {
        User savedUser = userRepository.save(testUser);
        assertTrue(userRepository.existsById(savedUser.getUserId()), "User should exist in the database");
    }

    @Test
    @DisplayName("Test deleting a User by ID")
    void testDeleteById() {
        User savedUser = userRepository.save(testUser);
        User.UserId userId = savedUser.getUserId();

        userRepository.deleteById(userId);
        assertFalse(userRepository.existsById(userId), "User should not exist after deletion");
    }

    @Test
    @DisplayName("Test finding non-existent User by ID")
    void testFindByIdNonExistent() {
        assertFalse(userRepository.findById(new User.UserId(999L)).isPresent(),
                "Non-existent user ID should return empty Optional");
    }
}
