package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.web.model.FoodOrder;
import com.web.model.User;
import com.web.repository.UserOrderRepository;

@Service
public class UserOrderService  {


    private final UserOrderRepository userOrderRepository;

    @Autowired
    public UserOrderService(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }

    // Get orders for a user
    public List<FoodOrder> getOrdersForUser(User user) {
        return userOrderRepository.findByUserId(user.getId());
    }

    // Delete order
    public void deleteOrder(Integer orderId, User user) {
        FoodOrder foodOrder = userOrderRepository.findById(orderId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Order not found"
            ));

        // ✅ Correct primitive comparison
        if (foodOrder.getUser().getId() != user.getId()) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN, "Order does not belong to user"
            );
        }

        if (!foodOrder.getStatus().replaceAll("\\s", "")
                .equalsIgnoreCase("Pending")) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Order cannot be deleted once processing has started"
            );
        }

        userOrderRepository.delete(foodOrder);
    }


}
