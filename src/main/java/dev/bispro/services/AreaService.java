package dev.bispro.services;

import dev.bispro.dtos.AreaDTO;

import java.util.List;

public interface AreaService {

    AreaDTO findById(Long areaId);

    AreaDTO createArea(AreaDTO areaDTO);

    AreaDTO updateArea(Long areaId, AreaDTO areaDTO);

    void deleteArea(Long areaId);

    List<AreaDTO> getAllAreas();
}
