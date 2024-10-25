package dev.bispro.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "textLayers")
@PrimaryKeyJoinColumn(name = "text_layer_id", foreignKey = @ForeignKey(name = "FK_textLayer_2_layer"))
public class TextLayer extends Layer {

    @Column(name = "text")
    private String text;

    @Column(name = "color")
    private String color;

    public TextLayer(String layername, Integer posX, Integer posY, Integer width, Integer height, Integer rotation, String fill, Integer opacity, LayerType layerType, String text, String color) {
        super(layername, posX, posY, width, height, rotation, fill, opacity, layerType);
        this.text = text;
        this.color = color;
    }

    public TextLayer() {

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
