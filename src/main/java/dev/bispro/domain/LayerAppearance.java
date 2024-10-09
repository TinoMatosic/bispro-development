package dev.bispro.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LayerAppearance {
    @Id
    private Long appearanceId;

    public void setAppearanceId(Long appearanceId) {
        this.appearanceId = appearanceId;
    }

    public Long getAppearanceId() {
        return appearanceId;
    }
}
