package dev.bispro.controllers;

import dev.bispro.dtos.AreaDTO;
import dev.bispro.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/areas")
@CrossOrigin
public class AreaController {

    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("/{areaId}")
    public ResponseEntity<AreaDTO> getAreaById(@PathVariable Long areaId) {
        return ResponseEntity.ok(areaService.findById(areaId));
    }

    @PostMapping
    public ResponseEntity<AreaDTO> createArea(@RequestBody AreaDTO areaDTO) {
        return ResponseEntity.ok(areaService.createArea(areaDTO));
    }

    @PutMapping("/{areaId}")
    public ResponseEntity<AreaDTO> updateArea(@PathVariable Long areaId, @RequestBody AreaDTO areaDTO) {
        return ResponseEntity.ok(areaService.updateArea(areaId, areaDTO));
    }

    @DeleteMapping("/{areaId}")
    public ResponseEntity<Void> deleteArea(@PathVariable Long areaId) {
        areaService.deleteArea(areaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AreaDTO>> getAllAreas() {
        return ResponseEntity.ok(areaService.getAllAreas());
    }
}
