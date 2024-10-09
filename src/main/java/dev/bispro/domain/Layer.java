package dev.bispro.domain;

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


    public void setLayerId(Long layerId) {
        this.layerId = layerId;
    }

    public Long getLayerId() {
        return layerId;
    }
}
