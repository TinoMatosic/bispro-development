package dev.bispro.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public enum LayerType {
    AREA,
    TABLE,
    TEXT
}
