package com.example.chicken.startup;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.chicken.service.FarmService;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final FarmService farmService;

    @Override
    public void run(String... args) {
        farmService.populateData();
    }
}
