package com.web.service;

import com.web.model.CreateFoodOrderRequest;
import com.web.model.FoodOrder;
import com.web.model.User;

import jakarta.servlet.http.HttpSession;

public interface OrderService {
	
	public FoodOrder createOrder(CreateFoodOrderRequest request, User loggedInUser);

	//FoodOrder createOrder(CreateFoodOrderRequest req, HttpSession session);

}
