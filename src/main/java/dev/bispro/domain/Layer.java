package dev.bispro.domain;

import dev.bispro.domain.exceptions.DataValidationException;
import dev.bispro.persistence.converters.LayerTypeConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "layers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Layer {

    @Id
    @Column(name = "layerId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long layerId;

    @Column(name = "layername")
    private String layername;

    @Column(name = "posX")
    private Integer posX;

    @Column(name = "posY")
    private Integer posY;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "rotation")
    private Integer rotation;

    @Column(name = "fill")
    private String fill;

    @Column(name = "opacity")
    private Integer opacity;

    @Column(columnDefinition = LayerTypeConverter.COLUMN_DEFINITION)
    @Embedded
    @Enumerated(EnumType.STRING)
    private LayerType layerType;

    public Layer(String layername, Integer posX, Integer posY, Integer width, Integer height, Integer rotation, String fill, Integer opacity, LayerType layerType) {
        setLayername(layername);
        setPosX(posX);
        setPosY(posY);
        setWidth(width);
        setHeight(height);
        setRotation(rotation);
        setFill(fill);
        setOpacity(opacity);
        setLayerType(layerType);
    }

    public Layer() {

    }

    public Long getLayerId() {
        return layerId;
    }

    public String getLayername() {
        return layername;
    }

    public void setLayername(String layername) {
        if (layername == null || layername.trim().isEmpty()) {
            throw DataValidationException.forInvalidInput("Layername cannot be null or empty.");
        }
        this.layername = layername;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        if (posX <= 0) {
            throw DataValidationException.forInvalidInput("PosX cannot be under or equal 0.");
        }
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        if (posY <= 0) {
            throw DataValidationException.forInvalidInput("PosY cannot be under or equal 0.");
        }
        this.posY = posY;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        if (width <= 0) {
            throw DataValidationException.forInvalidInput("Width cannot be under or equal 0.");
        }
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        if (height <= 0) {
            throw DataValidationException.forInvalidInput("Height cannot be under or equal 0.");
        }
        this.height = height;
    }

    public Integer getRotation() {
        return rotation;
    }

    public void setRotation(Integer rotation) {
        if (rotation < 0 || rotation > 359) {
            throw DataValidationException.forInvalidInput("Rotation cannot be under or equal 0.");
        }
        this.rotation = rotation;
    }

    public LayerType getLayerType() {
        return layerType;
    }

    public void setLayerType(LayerType layerType) {
        if (layerType == null) {
            throw DataValidationException.forInvalidInput("Layer Type cannot be null");
        }
        this.layerType = layerType;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        if (fill == null) {
            throw DataValidationException.forInvalidInput("Fill cannot be null");
        }
        this.fill = fill;
    }

    public Integer getOpacity() {
        return opacity;
    }

    public void setOpacity(Integer opacity) {
        if (opacity < 0 || opacity > 100) {
            throw DataValidationException.forInvalidInput("Opacity cannot be under 0 or above 100");
        }
        this.opacity = opacity;
    }
}
