package dev.bispro.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "layerAppearances")
public class Area extends Layer {

    @Column(name = "fill")
    private String fill;

    @Column(name = "corners")
    private Integer corners;

    public Area(String layername, Integer posX, Integer posY, Integer width, Integer height, Integer rotation, LayerType layerType, String fill, Integer opacity,  Integer corners) {
        super(layername, posX, posY, width, height, rotation, fill, opacity, layerType);
        this.fill = fill;
        this.corners = corners;
    }

    public Area() {

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
