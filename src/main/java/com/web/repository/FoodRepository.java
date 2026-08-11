package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.model.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {

}
