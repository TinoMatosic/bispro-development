package dev.bispro.dtos;

import dev.bispro.domain.LayerType;

public class TableLayerDTO extends LayerDTO {

    private Integer corners;
    private Boolean stroke;
    private Integer strokeWidth;
    private String strokeColor;
    private Integer tableNumber;

    public TableLayerDTO(Long layerId, String layername, Integer posX, Integer posY, Integer width, Integer height,
                         Integer rotation, String fill, Integer opacity, LayerType layerType,
                         Integer corners, Boolean stroke, Integer strokeWidth, String strokeColor, Integer tableNumber) {
        super(layerId, layername, posX, posY, width, height, rotation, fill, opacity, layerType);
        this.corners = corners;
        this.stroke = stroke;
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
        this.tableNumber = tableNumber;
    }

    public Integer getCorners() {
        return corners;
    }

    public void setCorners(Integer corners) {
        this.corners = corners;
    }

    public Boolean getStroke() {
        return stroke;
    }

    public void setStroke(Boolean stroke) {
        this.stroke = stroke;
    }

    public Integer getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(Integer strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }
}
