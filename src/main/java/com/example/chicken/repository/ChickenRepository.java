package com.example.chicken.repository;

import com.example.chicken.entity.Chicken;
import com.example.chicken.entity.Farm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChickenRepository extends JpaRepository<Chicken, Long> {
    Page<Chicken> findAll(Pageable pageable);  // Ajout de la pagination
    Page<Chicken> findAllByFarm(Farm farm, Pageable pageable);
}
