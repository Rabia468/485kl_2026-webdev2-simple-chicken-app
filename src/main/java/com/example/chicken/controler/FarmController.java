package com.example.chicken.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.chicken.entity.Farm;
import com.example.chicken.service.FarmService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    // 🔹 Récupérer toutes les fermes
    @GetMapping
    public List<Farm> getAllFarms() {
        return farmService.getAllFarms();
    }

    // 🔹 Récupérer une ferme par ID
    @GetMapping("/{id}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long id) {
        Optional<Farm> farm = farmService.getFarmById(id);
        return farm.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Ajouter une nouvelle ferme
    @PostMapping
    public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {
        Farm createdFarm = farmService.createFarm(farm);
        return ResponseEntity.ok(createdFarm);
    }

    // 🔹 Supprimer une ferme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        if (farmService.getFarmById(id).isPresent()) {
            farmService.deleteFarm(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
