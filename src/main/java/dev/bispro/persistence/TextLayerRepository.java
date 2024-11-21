package dev.bispro.persistence;

import dev.bispro.domain.TextLayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextLayerRepository extends JpaRepository<TextLayer, Long> { // Frage wegen der Vererbung bei Layer (für Long)
}
