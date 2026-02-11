package com.example.chicken.controler;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.chicken.entity.Chicken;
import com.example.chicken.entity.Farm;
import com.example.chicken.repository.ChickenRepository;
import com.example.chicken.repository.FarmRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/chickens")
public class ChickenController {

    private final ChickenRepository chickenRepository;
    private final FarmRepository farmRepository;

    public ChickenController(ChickenRepository chickenRepository, FarmRepository farmRepository) {
        this.chickenRepository = chickenRepository;
        this.farmRepository = farmRepository;
    }

    @GetMapping
    public Page<Chicken> getAllChickens(Pageable pageable) {
        return chickenRepository.findAll(pageable);
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<Page<Chicken>> getChickensByFarm(@PathVariable Long farmId, Pageable pageable) {
        Optional<Farm> farm = farmRepository.findById(farmId);
        if (farm.isPresent()) {
            return ResponseEntity.ok(chickenRepository.findAllByFarm(farm.get(), pageable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/farm/{farmId}")
    public ResponseEntity<Chicken> addChickenToFarm(@PathVariable Long farmId, @RequestBody Chicken chicken) {
        Optional<Farm> farm = farmRepository.findById(farmId);
        if (farm.isPresent()) {
            chicken.setFarm(farm.get());
            return ResponseEntity.ok(chickenRepository.save(chicken));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

