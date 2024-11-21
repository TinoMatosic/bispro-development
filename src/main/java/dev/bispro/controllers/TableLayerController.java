package dev.bispro.controllers;

import dev.bispro.dtos.TableLayerDTO;
import dev.bispro.services.TableLayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tableLayers")
@CrossOrigin
public class TableLayerController {

    private final TableLayerService tableLayerService;

    @Autowired
    public TableLayerController(TableLayerService tableLayerService) {
        this.tableLayerService = tableLayerService;
    }

    @GetMapping("/{tableLayerId}")
    public ResponseEntity<TableLayerDTO> getTableLayerById(@PathVariable Long tableLayerId) {
        return ResponseEntity.ok(tableLayerService.findById(tableLayerId));
    }

    @PostMapping
    public ResponseEntity<TableLayerDTO> createTableLayer(@RequestBody TableLayerDTO tableLayerDTO) {
        return ResponseEntity.ok(tableLayerService.createTableLayer(tableLayerDTO));
    }

    @PutMapping("/{tableLayerId}")
    public ResponseEntity<TableLayerDTO> updateTableLayer(@PathVariable Long tableLayerId, @RequestBody TableLayerDTO tableLayerDTO) {
        return ResponseEntity.ok(tableLayerService.updateTableLayer(tableLayerId, tableLayerDTO));
    }

    @DeleteMapping("/{tableLayerId}")
    public ResponseEntity<Void> deleteTableLayer(@PathVariable Long tableLayerId) {
        tableLayerService.deleteTableLayer(tableLayerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TableLayerDTO>> getAllTableLayers() {
        return ResponseEntity.ok(tableLayerService.getAllTableLayers());
    }
}
