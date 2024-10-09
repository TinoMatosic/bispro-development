package dev.bispro.domain;

import jakarta.persistence.*;

import jakarta.persistence.*;

@Entity
@Table(name = "la_layerAppearance")
public class LayerAppearance {

    @Id
    @Column(name = "la_layerAppId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appearanceId;

    @Column(name = "la_fill")
    private String fill;

    @Column(name = "la_corners")
    private Integer corners;

    public LayerAppearance(Long appearanceId, String fill, Integer corners) {
        this.appearanceId = appearanceId;
        this.fill = fill;
        this.corners = corners;
    }

    public LayerAppearance() {

    }

    public Long getAppearanceId() {
        return appearanceId;
    }
    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public Integer getCorners() {
        return corners;
    }

    public void setCorners(Integer corners) {
        this.corners = corners;
    }
}
