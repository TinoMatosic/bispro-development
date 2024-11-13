package dev.bispro.persistence;

import dev.bispro.domain.Email;
import dev.bispro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, User.UserId> {

    User findByEmail(Email email);
}
