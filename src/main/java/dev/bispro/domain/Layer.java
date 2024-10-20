package dev.bispro.domain;

import dev.bispro.domain.exceptions.DataValidationException;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "l_layers")
public class Layer {

    @Id
    @Column(name = "l_layerId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long layerId;

    @Column(name = "l_layername")
    private String layername;

    @Column(name = "l_posX")
    private Integer posX;

    @Column(name = "l_posY")
    private Integer posY;

    @Column(name = "l_width")
    private Integer width;

    @Column(name = "l_height")
    private Integer height;

    @Column(name = "l_appearance")
    @OneToMany
    private List<LayerAppearance> appearance;

    @Column(name = "l_rotation")
    private Integer rotation;


    public Layer(Long layerId, String layername, Integer posX, Integer posY, Integer width, Integer height, List<LayerAppearance> appearance, Integer rotation) {
        this.layerId = layerId;
        setLayername(layername);
        setPosX(posX);
        setPosY(posY);
        setWidth(width);
        setHeight(height);
        setAppearance(appearance);
        setRotation(rotation);
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

    public List<LayerAppearance> getAppearance() {
        return appearance;
    }

    public void setAppearance(List<LayerAppearance> appearance) {
        if (appearance == null) {
            throw DataValidationException.forInvalidInput("Appearance list cannot be null.");
        }
        this.appearance = appearance;
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
}
