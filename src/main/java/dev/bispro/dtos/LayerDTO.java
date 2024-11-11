package dev.bispro.dtos;

import dev.bispro.domain.LayerType;

public class LayerDTO {

    private Long layerId;
    private String layername;
    private Integer posX;
    private Integer posY;
    private Integer width;
    private Integer height;
    private Integer rotation;
    private String fill;
    private Integer opacity;
    private LayerType layerType;

    // Constructor with all fields
    public LayerDTO(Long layerId, String layername, Integer posX, Integer posY, Integer width, Integer height,
                    Integer rotation, String fill, Integer opacity, LayerType layerType) {
        this.layerId = layerId;
        this.layername = layername;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.fill = fill;
        this.opacity = opacity;
        this.layerType = layerType;
    }

    // Getters and Setters
    public Long getLayerId() {
        return layerId;
    }

    public void setLayerId(Long layerId) {
        this.layerId = layerId;
    }

    public String getLayername() {
        return layername;
    }

    public void setLayername(String layername) {
        this.layername = layername;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getRotation() {
        return rotation;
    }

    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public Integer getOpacity() {
        return opacity;
    }

    public void setOpacity(Integer opacity) {
        this.opacity = opacity;
    }

    public LayerType getLayerType() {
        return layerType;
    }

    public void setLayerType(LayerType layerType) {
        this.layerType = layerType;
    }
}
