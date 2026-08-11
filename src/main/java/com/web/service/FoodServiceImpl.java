package com.web.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.model.Food;
import com.web.repository.FoodRepository;
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    private final String uploadDir = "uploads/";

    @Override
    public void saveFood(String title, String description, double price, double discount, boolean isActive,
                         int stock, int categoryId, MultipartFile file) throws IOException {

        Food food = new Food();
        food.setTitle(title);
        food.setDescription(description);
        food.setPrice(price);
        food.setDiscount(discount);

        // calculate discount price
        food.setDiscountPrice(price - (price * discount / 100));

        food.setIsActive(isActive);
        food.setStock(stock);
        food.setCategoryId(categoryId);

        // 🔥 SAME METHOD AS CATEGORY SERVICE
        if (file != null && !file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            food.setImage(fileName);
        }

        foodRepository.save(food);
    }

    @Override
    public void updateFood(int id, String title, String description, double price, double discount, boolean isActive,
                           int stock, int categoryId, MultipartFile file) throws IOException {

        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        food.setTitle(title);
        food.setDescription(description);
        food.setPrice(price);
        food.setDiscount(discount);
        food.setDiscountPrice(price - (price * discount / 100));
        food.setIsActive(isActive);
        food.setStock(stock);
        food.setCategoryId(categoryId);

        // 🔥 SAME FIX AS ABOVE
        if (file != null && !file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            food.setImage(fileName);
        }

        foodRepository.save(food);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public Food getFoodById(int id) {
        return foodRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteFood(int id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
