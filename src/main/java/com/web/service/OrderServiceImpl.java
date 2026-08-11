package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.CartItem;
import com.web.model.CreateFoodOrderRequest;
import com.web.model.Food;
import com.web.model.FoodOrder;
import com.web.model.OrderAddress;
import com.web.model.OrderFoodItem;
import com.web.model.User;
import com.web.repository.FoodOrderRepository;
import com.web.repository.FoodRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private FoodOrderRepository orderRepo;

    @Autowired
    private FoodRepository foodRepo;

    @Override
    public FoodOrder createOrder(CreateFoodOrderRequest req, User loggedInUser) {

        // --------------------- ADDRESS ---------------------
        OrderAddress address = new OrderAddress();
        address.setFullName(req.getFullName());
        address.setPhone(req.getPhone());
        address.setEmail(req.getEmail());
        address.setAddress(req.getAddress());

        // --------------------- MAIN ORDER ---------------------
        FoodOrder order = new FoodOrder();
        order.setOrderId("ORD-" + System.currentTimeMillis());
        order.setStatus("Pending");
        order.setPaymentType(req.getPaymentType());
        order.setAddress(address);
        order.setGrandTotal(0);
        
     // ATTACH LOGGED-IN USER
        order.setUser(loggedInUser);

        double total = 0;

        // --------------------- ORDER ITEMS ---------------------
        for (CartItem c : req.getCart()) {

            // Try to fetch food, skip if it doesn't exist
            Food food = foodRepo.findById(c.getFoodId())
            		.orElseThrow(() -> new RuntimeException("Food not found: ID " + c.getFoodId()));


            if (food == null) {
                System.out.println("Skipping invalid foodId: " + c.getFoodId());
                continue; // skip invalid food
            }

            OrderFoodItem item = new OrderFoodItem();
            item.setFood(food);
            item.setQuantity(c.getQty());
            item.setPrice(c.getPrice());
            item.setOrderFood(order);
            order.addItem(item);

            total += c.getPrice() * c.getQty();
        }

        order.setGrandTotal(total);

        // --------------------- SAVE ORDER ---------------------
        return orderRepo.save(order);
    }

	
}
