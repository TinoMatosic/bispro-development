package dev.bispro.controllers;

import dev.bispro.dtos.TextLayerDTO;
import dev.bispro.services.TextLayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/textLayers")
@CrossOrigin
public class TextLayerController {

    private final TextLayerService textLayerService;

    @Autowired
    public TextLayerController(TextLayerService textLayerService) {
        this.textLayerService = textLayerService;
    }

    @GetMapping("/{textLayerId}")
    public ResponseEntity<TextLayerDTO> getTextLayerById(@PathVariable Long textLayerId) {
        return ResponseEntity.ok(textLayerService.findById(textLayerId));
    }

    @PostMapping
    public ResponseEntity<TextLayerDTO> createTextLayer(@RequestBody TextLayerDTO textLayerDTO) {
        return ResponseEntity.ok(textLayerService.createTextLayer(textLayerDTO));
    }

    @PutMapping("/{textLayerId}")
    public ResponseEntity<TextLayerDTO> updateTextLayer(@PathVariable Long textLayerId, @RequestBody TextLayerDTO textLayerDTO) {
        return ResponseEntity.ok(textLayerService.updateTextLayer(textLayerId, textLayerDTO));
    }

    @DeleteMapping("/{textLayerId}")
    public ResponseEntity<Void> deleteTextLayer(@PathVariable Long textLayerId) {
        textLayerService.deleteTextLayer(textLayerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TextLayerDTO>> getAllTextLayers() {
        return ResponseEntity.ok(textLayerService.getAllTextLayers());
    }
}
