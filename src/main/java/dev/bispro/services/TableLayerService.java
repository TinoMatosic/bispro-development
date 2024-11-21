package dev.bispro.services;

import dev.bispro.dtos.TableLayerDTO;

import java.util.List;

public interface TableLayerService {

    TableLayerDTO findById(Long tableLayerId);

    TableLayerDTO createTableLayer(TableLayerDTO tableLayerDTO);

    TableLayerDTO updateTableLayer(Long tableLayerId, TableLayerDTO tableLayerDTO);

    void deleteTableLayer(Long tableLayerId);

    List<TableLayerDTO> getAllTableLayers();
}
