package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.model.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer> {

}
