package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.model.OrderFoodItem;

public interface OrderFoodItemRepository  extends JpaRepository<OrderFoodItem, Integer>{

}
