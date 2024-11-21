package dev.bispro.persistence;

import dev.bispro.domain.TableLayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableLayerRepository extends JpaRepository<TableLayer, Long> {  // Frage wegen der Vererbung bei Layer (für Long)
}
