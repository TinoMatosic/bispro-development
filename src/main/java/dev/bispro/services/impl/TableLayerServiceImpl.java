package dev.bispro.services.impl;

import dev.bispro.domain.TableLayer;
import dev.bispro.dtos.TableLayerDTO;
import dev.bispro.persistence.TableLayerRepository;
import dev.bispro.services.TableLayerService;
import dev.bispro.services.exceptions.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableLayerServiceImpl implements TableLayerService {

    private final TableLayerRepository tableLayerRepository;

    @Autowired
    public TableLayerServiceImpl(TableLayerRepository tableLayerRepository) {
        this.tableLayerRepository = tableLayerRepository;
    }

    @Override
    public TableLayerDTO findById(Long tableLayerId) {
        TableLayer tableLayer = tableLayerRepository.findById(tableLayerId)
                .orElseThrow(() -> ServiceLayerException.notFound("TableLayer not found with ID: " + tableLayerId));
        return toTableLayerDTO(tableLayer);
    }

    @Override
    public TableLayerDTO createTableLayer(TableLayerDTO tableLayerDTO) {
        TableLayer tableLayer = toTableLayerEntity(tableLayerDTO);
        TableLayer savedTableLayer = tableLayerRepository.save(tableLayer);
        return toTableLayerDTO(savedTableLayer);
    }

    @Override
    public TableLayerDTO updateTableLayer(Long tableLayerId, TableLayerDTO tableLayerDTO) {
        TableLayer tableLayer = tableLayerRepository.findById(tableLayerId)
                .orElseThrow(() -> ServiceLayerException.notFound("TableLayer not found with ID: " + tableLayerId));

        updateTableLayerFields(tableLayer, tableLayerDTO);
        TableLayer updatedTableLayer = tableLayerRepository.save(tableLayer);
        return toTableLayerDTO(updatedTableLayer);
    }

    @Override
    public void deleteTableLayer(Long tableLayerId) {
        if (!tableLayerRepository.existsById(tableLayerId)) {
            throw ServiceLayerException.notFound("TableLayer not found with ID: " + tableLayerId);
        }
        tableLayerRepository.deleteById(tableLayerId);
    }

    @Override
    public List<TableLayerDTO> getAllTableLayers() {
        return tableLayerRepository.findAll().stream()
                .map(this::toTableLayerDTO)
                .collect(Collectors.toList());
    }

    private TableLayerDTO toTableLayerDTO(TableLayer tableLayer) {
        return new TableLayerDTO(
                tableLayer.getLayerId(),
                tableLayer.getLayername(),
                tableLayer.getPosX(),
                tableLayer.getPosY(),
                tableLayer.getWidth(),
                tableLayer.getHeight(),
                tableLayer.getRotation(),
                tableLayer.getFill(),
                tableLayer.getOpacity(),
                tableLayer.getLayerType(),
                tableLayer.getCorners(),
                tableLayer.getStroke(),
                tableLayer.getStrokeWidth(),
                tableLayer.getStrokeColor(),
                tableLayer.getTableNumber()
        );
    }

    private TableLayer toTableLayerEntity(TableLayerDTO tableLayerDTO) {
        return new TableLayer(
                tableLayerDTO.getLayername(),
                tableLayerDTO.getPosX(),
                tableLayerDTO.getPosY(),
                tableLayerDTO.getWidth(),
                tableLayerDTO.getHeight(),
                tableLayerDTO.getRotation(),
                tableLayerDTO.getFill(),
                tableLayerDTO.getOpacity(),
                tableLayerDTO.getLayerType(),
                tableLayerDTO.getCorners(),
                tableLayerDTO.getStroke(),
                tableLayerDTO.getStrokeWidth(),
                tableLayerDTO.getStrokeColor(),
                tableLayerDTO.getTableNumber()
        );
    }

    private void updateTableLayerFields(TableLayer tableLayer, TableLayerDTO tableLayerDTO) {
        tableLayer.setLayername(tableLayerDTO.getLayername());
        tableLayer.setPosX(tableLayerDTO.getPosX());
        tableLayer.setPosY(tableLayerDTO.getPosY());
        tableLayer.setWidth(tableLayerDTO.getWidth());
        tableLayer.setHeight(tableLayerDTO.getHeight());
        tableLayer.setRotation(tableLayerDTO.getRotation());
        tableLayer.setFill(tableLayerDTO.getFill());
        tableLayer.setOpacity(tableLayerDTO.getOpacity());
        tableLayer.setLayerType(tableLayerDTO.getLayerType());
        tableLayer.setCorners(tableLayerDTO.getCorners());
        tableLayer.setStroke(tableLayerDTO.getStroke());
        tableLayer.setStrokeWidth(tableLayerDTO.getStrokeWidth());
        tableLayer.setStrokeColor(tableLayerDTO.getStrokeColor());
        tableLayer.setTableNumber(tableLayerDTO.getTableNumber());
    }
}
