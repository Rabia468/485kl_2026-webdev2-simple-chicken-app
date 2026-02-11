package com.example.chicken.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chicken.entity.Chicken;
import com.example.chicken.entity.Farm;
import com.example.chicken.repository.FarmRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class FarmService {

    private final FarmRepository farmRepository;

    public FarmService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    public Optional<Farm> getFarmById(Long id) {
        return farmRepository.findById(id);
    }

    public Farm createFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    public void deleteFarm(Long id) {
        farmRepository.deleteById(id);
    }

    @Transactional
    public void populateData() {
        List<String> farmNames = List.of("Sunny Farm", "Happy Henhouse", "Golden Coop");
        List<String> chickenNames = new ArrayList<>(List.of("Fluffy", "Pecky", "Nugget", "Chickpea", "Eggbert", "Feathers", "BawkBawk",
                "Drumstick", "Cluckles", "Wingman", "Plucky", "Hen Solo", "Chickira",
                "Yolkie", "Beaker"));
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            Farm farm = new Farm();
            farm.setName(farmNames.get(i));

            for (int j = 0; j < 5; j++) {
                Chicken chicken = new Chicken();
                String chickenName = chickenNames.remove(random.nextInt(chickenNames.size())); // Unique name
                chicken.setName(chickenName);
                chicken.setAge(random.nextInt(3) + 1); // Age between 1 and 3
                chicken.setWeight(1.0 + (random.nextDouble() * 1.5)); // Weight between 1.0 and 2.5

                farm.addChicken(chicken);
            }
            farmRepository.save(farm);
        }
    }
}
