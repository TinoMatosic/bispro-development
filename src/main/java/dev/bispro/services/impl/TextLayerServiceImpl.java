package dev.bispro.services.impl;

import dev.bispro.domain.TextLayer;
import dev.bispro.dtos.TextLayerDTO;
import dev.bispro.persistence.TextLayerRepository;
import dev.bispro.services.TextLayerService;
import dev.bispro.services.exceptions.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextLayerServiceImpl implements TextLayerService {

    private final TextLayerRepository textLayerRepository;

    @Autowired
    public TextLayerServiceImpl(TextLayerRepository textLayerRepository) {
        this.textLayerRepository = textLayerRepository;
    }

    @Override
    public TextLayerDTO findById(Long textLayerId) {
        TextLayer textLayer = textLayerRepository.findById(textLayerId)
                .orElseThrow(() -> ServiceLayerException.notFound("TextLayer not found with ID: " + textLayerId));
        return toTextLayerDTO(textLayer);
    }

    @Override
    public TextLayerDTO createTextLayer(TextLayerDTO textLayerDTO) {
        TextLayer textLayer = toTextLayerEntity(textLayerDTO);
        TextLayer savedTextLayer = textLayerRepository.save(textLayer);
        return toTextLayerDTO(savedTextLayer);
    }

    @Override
    public TextLayerDTO updateTextLayer(Long textLayerId, TextLayerDTO textLayerDTO) {
        TextLayer textLayer = textLayerRepository.findById(textLayerId)
                .orElseThrow(() -> ServiceLayerException.notFound("TextLayer not found with ID: " + textLayerId));

        updateTextLayerFields(textLayer, textLayerDTO);
        TextLayer updatedTextLayer = textLayerRepository.save(textLayer);
        return toTextLayerDTO(updatedTextLayer);
    }

    @Override
    public void deleteTextLayer(Long textLayerId) {
        if (!textLayerRepository.existsById(textLayerId)) {
            throw ServiceLayerException.notFound("TextLayer not found with ID: " + textLayerId);
        }
        textLayerRepository.deleteById(textLayerId);
    }

    @Override
    public List<TextLayerDTO> getAllTextLayers() {
        return textLayerRepository.findAll().stream()
                .map(this::toTextLayerDTO)
                .collect(Collectors.toList());
    }

    private TextLayerDTO toTextLayerDTO(TextLayer textLayer) {
        return new TextLayerDTO(
                textLayer.getLayerId(),
                textLayer.getLayername(),
                textLayer.getPosX(),
                textLayer.getPosY(),
                textLayer.getWidth(),
                textLayer.getHeight(),
                textLayer.getRotation(),
                textLayer.getFill(),
                textLayer.getOpacity(),
                textLayer.getLayerType(),
                textLayer.getText(),
                textLayer.getColor()
        );
    }

    private TextLayer toTextLayerEntity(TextLayerDTO textLayerDTO) {
        return new TextLayer(
                textLayerDTO.getLayername(),
                textLayerDTO.getPosX(),
                textLayerDTO.getPosY(),
                textLayerDTO.getWidth(),
                textLayerDTO.getHeight(),
                textLayerDTO.getRotation(),
                textLayerDTO.getFill(),
                textLayerDTO.getOpacity(),
                textLayerDTO.getLayerType(),
                textLayerDTO.getText(),
                textLayerDTO.getColor()
        );
    }

    private void updateTextLayerFields(TextLayer textLayer, TextLayerDTO textLayerDTO) {
        textLayer.setLayername(textLayerDTO.getLayername());
        textLayer.setPosX(textLayerDTO.getPosX());
        textLayer.setPosY(textLayerDTO.getPosY());
        textLayer.setWidth(textLayerDTO.getWidth());
        textLayer.setHeight(textLayerDTO.getHeight());
        textLayer.setRotation(textLayerDTO.getRotation());
        textLayer.setFill(textLayerDTO.getFill());
        textLayer.setOpacity(textLayerDTO.getOpacity());
        textLayer.setLayerType(textLayerDTO.getLayerType());
        textLayer.setText(textLayerDTO.getText());
        textLayer.setColor(textLayerDTO.getColor());
    }
}
