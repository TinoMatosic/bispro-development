package dev.bispro.persistence;

import dev.bispro.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    private @Autowired UserRepository userRepository;

    @Test
    void can_save() {

        var user = new User(
                "Tino",
                "Matosic",
                "tino.mat@icloud.com",
                "12345678",
                Role.USER,
                Plan.PRO,
                new Restaurant());

        var savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUserId()).isPositive();
    }
}