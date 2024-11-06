package dev.bispro.persistence;

import dev.bispro.domain.Employee;
import dev.bispro.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
