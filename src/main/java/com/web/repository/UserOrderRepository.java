package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.model.FoodOrder;
import com.web.model.User;

public interface UserOrderRepository extends JpaRepository<FoodOrder, Integer> {
	List<FoodOrder> findByUserId(Integer userId);
}
