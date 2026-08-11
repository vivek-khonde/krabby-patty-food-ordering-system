package com.web.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "order_food_item")
public class OrderFoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)  // maps to food table
    private Food food;

    private int quantity;

    private double price;

    private double totalPrice;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)  // maps to food_orders table
    private FoodOrder orderFood;

    @PrePersist
    public void calculateTotal() {
        this.totalPrice = this.price * this.quantity;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Food getFood() { return food; }
    public void setFood(Food food) { this.food = food; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public FoodOrder getOrderFood() { return orderFood; }
    public void setOrderFood(FoodOrder orderFood) { this.orderFood = orderFood; }
}
