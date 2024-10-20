package dev.bispro.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Role {
    ADMIN,
    USER,
    LOCKED
}
