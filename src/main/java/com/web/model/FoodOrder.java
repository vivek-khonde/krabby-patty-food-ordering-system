package com.web.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "food_orders")
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String orderId;

    private LocalDateTime date;

    private String status;

    private String paymentType;

    private double grandTotal;

    @ManyToOne
    @JoinColumn(name = "user_id") // Link the order to a user
    private User user;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "address_id")  // optional, ensures correct FK
    private OrderAddress address;

    @JsonManagedReference
    @OneToMany(mappedBy = "orderFood", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("orderFood")
    private List<OrderFoodItem> items = new ArrayList<>();

    public FoodOrder() {
        this.date = LocalDateTime.now();
    }

    public void addItem(OrderFoodItem item) {
        item.setOrderFood(this);
        this.items.add(item);
    }

    // Getters & Setters
    // -------------------------
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }

    public double getGrandTotal() { return grandTotal; }
    public void setGrandTotal(double grandTotal) { this.grandTotal = grandTotal; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public OrderAddress getAddress() { return address; }
    public void setAddress(OrderAddress address) { this.address = address; }

    public List<OrderFoodItem> getItems() { return items; }
    public void setItems(List<OrderFoodItem> items) { this.items = items; }
}
