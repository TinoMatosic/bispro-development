package dev.bispro.dtos;

import dev.bispro.domain.LayerType;

public class AreaDTO extends LayerDTO {

    private String fill;
    private Integer corners;

    public AreaDTO(Long layerId, String layername, Integer posX, Integer posY, Integer width, Integer height,
                   Integer rotation, String fill, Integer opacity, LayerType layerType, String areaFill, Integer corners) {
        super(layerId, layername, posX, posY, width, height, rotation, fill, opacity, layerType);
        this.fill = areaFill;
        this.corners = corners;
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
