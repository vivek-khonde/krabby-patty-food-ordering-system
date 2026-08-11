package com.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.web.model.Food;
import com.web.service.FoodService;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    // Add Food
    @PostMapping
    public ResponseEntity<?> addFood(@RequestParam String title,
                                     @RequestParam String description,
                                     @RequestParam double price,
                                     @RequestParam double discount,
                                     @RequestParam boolean isActive,
                                     @RequestParam int stock,
                                     @RequestParam int categoryId,
                                     @RequestParam(required = false) MultipartFile file) {
        try {
            foodService.saveFood(title, description, price, discount, isActive, stock, categoryId, file);
            return ResponseEntity.ok("Food added successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error saving food");
        }
    }

    // Update Food
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable int id,
                                        @RequestParam String title,
                                        @RequestParam String description,
                                        @RequestParam double price,
                                        @RequestParam double discount,
                                        @RequestParam boolean isActive,
                                        @RequestParam int stock,
                                        @RequestParam int categoryId,
                                        @RequestParam(required = false) MultipartFile file) {
        try {
            foodService.updateFood(id, title, description, price, discount, isActive, stock, categoryId, file);
            return ResponseEntity.ok("Food updated successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error updating food");
        }
    }

    // Get All Foods
    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    // Get Food by ID
    @GetMapping("/{id}")
    public ResponseEntity<Food> getFood(@PathVariable int id) {
        Food food = foodService.getFoodById(id);
        if (food == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(food);
    }

    // Delete Food
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable int id) {
        if (foodService.deleteFood(id)) return ResponseEntity.ok("Deleted successfully");
        return ResponseEntity.status(404).body("Food not found");
    }
}
