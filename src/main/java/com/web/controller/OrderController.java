package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.web.model.CreateFoodOrderRequest;
import com.web.model.FoodOrder;
import com.web.model.User;
import com.web.repository.FoodOrderRepository;
import com.web.repository.UserOrderRepository;
import com.web.service.OrderService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodOrderRepository orderRepo;
    
    @Autowired
    private UserOrderRepository userOrderRepo;

    // CREATE ORDER
    @PostMapping
    public FoodOrder placeOrder(@RequestBody CreateFoodOrderRequest req,  HttpSession session) {
    	// Get logged-in user from session
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            throw new RuntimeException("User not logged in");
        }

        // Pass the user to the service when creating the order
        return orderService.createOrder(req, loggedInUser);
    }

    // GET ALL ORDERS FOR ADMIN
    @GetMapping
    public List<FoodOrder> getOrders() {
        return orderRepo.findAll();
    }

 
    // UPDATE STATUS
    @PatchMapping("/{id}")
    public String updateStatus(@PathVariable int id, @RequestBody FoodOrder req) {
        FoodOrder order = orderRepo.findById(id).orElseThrow();
        order.setStatus(req.getStatus());
        orderRepo.save(order);
        return "Status Updated";
    }

    // DELETE ORDER
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable int id) {
        orderRepo.deleteById(id);
        return "Order Deleted";
    }
}
