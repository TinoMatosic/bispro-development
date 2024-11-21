package dev.bispro.services;

import dev.bispro.dtos.TextLayerDTO;

import java.util.List;

public interface TextLayerService {

    TextLayerDTO findById(Long textLayerId);

    TextLayerDTO createTextLayer(TextLayerDTO textLayerDTO);

    TextLayerDTO updateTextLayer(Long textLayerId, TextLayerDTO textLayerDTO);

    void deleteTextLayer(Long textLayerId);

    List<TextLayerDTO> getAllTextLayers();
}
