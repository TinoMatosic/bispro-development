package dev.bispro.domain;

import dev.bispro.domain.exceptions.DataValidationException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.awt.*;

@Entity
@Table(name = "tableLayers")
public class TableLayer extends Layer {

    @Column(name = "corner")
    private Integer corners;

    @Column(name = "stroke")
    private Boolean stroke;

    @Column(name = "strokeWidth")
    private Integer strokeWidth;

    @Column(name = "strokeColor")
    private String strokeColor;

    @Column(name = "tableNumber")
    private Integer tableNumber;


    public TableLayer(String layername, Integer posX, Integer posY, Integer width, Integer height, Integer rotation, String fill, Integer opacity, LayerType layerType, Integer corners, Boolean stroke, Integer strokeWidth, String strokeColor, Integer tableNumber) {
        super(layername, posX, posY, width, height, rotation, fill, opacity, layerType);
        setCorners(corners);
        setStroke(stroke);
        setStrokeWidth(strokeWidth);
        setStrokeColor(strokeColor);
        setTableNumber(tableNumber);
    }

    public TableLayer() {

    }

    public Integer getCorners() {
        return corners;
    }

    public void setCorners(Integer corners) {
        if (corners < 0) {
            throw DataValidationException.forInvalidInput("Corners cannot be under 0");
        }
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
        if (strokeWidth < 0) {
            throw DataValidationException.forInvalidInput("Stroke Width cannot be under 0");
        }
        this.strokeWidth = strokeWidth;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        if (strokeColor == null || strokeColor.trim().isEmpty()) {
            throw DataValidationException.forInvalidInput("Stroke Color cannot be null or empty");
        }
        this.strokeColor = strokeColor;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        if (tableNumber < 0) {
            throw DataValidationException.forInvalidInput("Table Number cannot be null");
        }
        this.tableNumber = tableNumber;
    }
}
