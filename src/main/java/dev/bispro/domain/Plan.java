package dev.bispro.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Plan {
    FREE,
    PRO,
    PREMIUM
}
