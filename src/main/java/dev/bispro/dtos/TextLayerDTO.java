package dev.bispro.dtos;

import dev.bispro.domain.LayerType;

public class TextLayerDTO extends LayerDTO {

    private String text;
    private String color;

    public TextLayerDTO(Long layerId, String layername, Integer posX, Integer posY, Integer width, Integer height,
                        Integer rotation, String fill, Integer opacity, LayerType layerType,
                        String text, String color) {
        super(layerId, layername, posX, posY, width, height, rotation, fill, opacity, layerType);
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
