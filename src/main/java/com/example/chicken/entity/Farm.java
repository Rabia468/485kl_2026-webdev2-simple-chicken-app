package com.example.chicken.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "farms")
@Data
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Chicken> chickens = new ArrayList<>();

    public Farm() {}

    public Farm(String name) {
        this.name = name;
    }

    public void addChicken(Chicken chicken) {
        chickens.add(chicken);
        chicken.setFarm(this);
    }
    

    // Getters et Setters
}
