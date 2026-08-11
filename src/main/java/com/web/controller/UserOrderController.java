package com.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.web.model.FoodOrder;
import com.web.model.User;
import com.web.repository.UserOrderRepository;
import com.web.service.UserOrderService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user/orders")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserOrderController {

    private final UserOrderService userOrderService;
    private final UserOrderRepository userOrderRepo;

    @Autowired
    public UserOrderController(UserOrderService userOrderService, UserOrderRepository userOrderRepo) {
        this.userOrderService = userOrderService;
        this.userOrderRepo = userOrderRepo;
    }

    // Fetch orders for logged-in user
    @GetMapping
    public ResponseEntity<?> getUserOrders(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        System.out.println("Session user object: " + user); // DEBUG
        if (user == null) {
        	System.out.println("No user in session!"); // DEBUG
            return ResponseEntity.status(401).body("User not logged in");
        }
        
        System.out.println("User ID from session: " + user.getId()); // DEBUG

        // Fetch orders by user ID to avoid object mismatch
        List<FoodOrder> orders = userOrderRepo.findByUserId(user.getId());
        System.out.println("Number of orders found: " + orders.size()); // DEBUG
        
        if (orders.isEmpty()) {
            return ResponseEntity.ok("No orders found for this user");
        }

        return ResponseEntity.ok(orders);
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return ResponseEntity.status(401).body("User not logged in");
        }

        try {
            userOrderService.deleteOrder(id, user); // may throw exception
            return ResponseEntity.ok("Order deleted successfully");
        } catch (ResponseStatusException ex) {
            // Send proper status and message
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        } catch (RuntimeException ex) {
            // For other runtime errors
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

}
