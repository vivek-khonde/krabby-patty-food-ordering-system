package com.web.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.web.model.Food;

public interface FoodService {
	
	void saveFood(String title, String description, double price, double discount, boolean isActive,
            int stock, int categoryId, MultipartFile file) throws IOException;

void updateFood(int id, String title, String description, double price, double discount, boolean isActive,
              int stock, int categoryId, MultipartFile file) throws IOException;

List<Food> getAllFoods();
Food getFoodById(int id);
boolean deleteFood(int id);
}
