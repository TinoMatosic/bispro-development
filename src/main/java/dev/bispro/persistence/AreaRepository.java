package dev.bispro.persistence;

import dev.bispro.domain.Area;
import dev.bispro.domain.Layer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Long> { // Frage wegen der Vererbung bei Layer (für Long)
}
