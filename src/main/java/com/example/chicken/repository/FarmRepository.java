package com.example.chicken.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chicken.entity.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
