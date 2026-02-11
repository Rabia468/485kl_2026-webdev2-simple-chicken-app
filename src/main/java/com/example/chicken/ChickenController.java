package com.example.chicken;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.net.URI;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*") // Autoriser toutes les origines (à restreindre en production)
@RestController
@RequestMapping("/api/chickens")
class ChickenController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getAllChickens() {
        return jdbcTemplate.queryForList("SELECT * FROM chickens");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getChicken(@PathVariable Long id) {
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM chickens WHERE id = ?", id);

        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Fix: Return 404 instead of causing 500 error
        }
        return ResponseEntity.ok(results.get(0));

    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addChicken(@RequestBody Map<String, Object> chicken) {

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            var ps = con.prepareStatement(
                "INSERT INTO chickens (name, age, weight) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, (String) chicken.get("NAME"));
            ps.setInt(2, Integer.parseInt(chicken.get("AGE").toString()));
            ps.setDouble(3, Double.parseDouble(chicken.get("WEIGHT").toString()));
            return ps;
        }, keyHolder);

        var generatedId = keyHolder.getKey().intValue();

        // Construct response with ID
        Map<String, Object> response = new HashMap<>();
        response.put("ID", generatedId);
        response.put("NAME", chicken.get("NAME"));
        response.put("AGE", chicken.get("AGE"));
        response.put("WEIGHT", chicken.get("WEIGHT"));

        // Return 201 Created with Location header
        return ResponseEntity
                .created(URI.create("/api/chickens/" + generatedId))
                .body(response);
    }

    @DeleteMapping("/{id}")
    public void deleteChicken(@PathVariable Long id) {
        jdbcTemplate.update("DELETE FROM chickens WHERE id = ?", id);
    }
}
