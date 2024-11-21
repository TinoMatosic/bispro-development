package dev.bispro.services.impl;

import dev.bispro.domain.Area;
import dev.bispro.dtos.AreaDTO;
import dev.bispro.persistence.AreaRepository;
import dev.bispro.services.AreaService;
import dev.bispro.services.exceptions.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;

    @Autowired
    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public AreaDTO findById(Long areaId) {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> ServiceLayerException.notFound("Area not found with ID: " + areaId));
        return toAreaDTO(area);
    }

    @Override
    public AreaDTO createArea(AreaDTO areaDTO) {
        Area area = toAreaEntity(areaDTO);
        Area savedArea = areaRepository.save(area);
        return toAreaDTO(savedArea);
    }

    @Override
    public AreaDTO updateArea(Long areaId, AreaDTO areaDTO) {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> ServiceLayerException.notFound("Area not found with ID: " + areaId));

        updateAreaFields(area, areaDTO);
        Area updatedArea = areaRepository.save(area);
        return toAreaDTO(updatedArea);
    }

    @Override
    public void deleteArea(Long areaId) {
        if (!areaRepository.existsById(areaId)) {
            throw ServiceLayerException.notFound("Area not found with ID: " + areaId);
        }
        areaRepository.deleteById(areaId);
    }

    @Override
    public List<AreaDTO> getAllAreas() {
        return areaRepository.findAll().stream()
                .map(this::toAreaDTO)
                .collect(Collectors.toList());
    }

    private AreaDTO toAreaDTO(Area area) {
        return new AreaDTO(
                area.getLayerId(),
                area.getLayername(),
                area.getPosX(),
                area.getPosY(),
                area.getWidth(),
                area.getHeight(),
                area.getRotation(),
                area.getFill(),
                area.getOpacity(),
                area.getLayerType(),
                area.getFill(),
                area.getCorners()
        );
    }

    private Area toAreaEntity(AreaDTO areaDTO) {
        return new Area(
                areaDTO.getLayername(),
                areaDTO.getPosX(),
                areaDTO.getPosY(),
                areaDTO.getWidth(),
                areaDTO.getHeight(),
                areaDTO.getRotation(),
                areaDTO.getLayerType(),
                areaDTO.getFill(),
                areaDTO.getOpacity(),
                areaDTO.getCorners()
        );
    }

    private void updateAreaFields(Area area, AreaDTO areaDTO) {
        area.setLayername(areaDTO.getLayername());
        area.setPosX(areaDTO.getPosX());
        area.setPosY(areaDTO.getPosY());
        area.setWidth(areaDTO.getWidth());
        area.setHeight(areaDTO.getHeight());
        area.setRotation(areaDTO.getRotation());
        area.setFill(areaDTO.getFill());
        area.setOpacity(areaDTO.getOpacity());
        area.setLayerType(areaDTO.getLayerType());
        area.setCorners(areaDTO.getCorners());
    }
}
