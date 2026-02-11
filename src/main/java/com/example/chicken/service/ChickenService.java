package com.example.chicken.service;

import com.example.chicken.entity.Chicken;
import com.example.chicken.repository.ChickenRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
public class ChickenService {

    private final ChickenRepository chickenRepository;

    public ChickenService(ChickenRepository chickenRepository) {
        this.chickenRepository = chickenRepository;
    }

    public Page<Chicken> getAllChickens(Pageable pageable) {
        return chickenRepository.findAll(pageable);
    }

    public Optional<Chicken> getChickenById(Long id) {
        return chickenRepository.findById(id);
    }

    public Chicken addChicken(Chicken chicken) {
        return chickenRepository.save(chicken);
    }

    public void deleteChicken(Long id) {
        chickenRepository.deleteById(id);
    }
}
