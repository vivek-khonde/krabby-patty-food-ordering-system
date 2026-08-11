package com.web.model;

import jakarta.persistence.*;

@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private double price;
    private double discount;
    private double discountPrice;
    private boolean isActive;
    private int stock;

    private String image; // store filename

    private int categoryId; // link to Category

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public double getDiscountPrice() { return discountPrice; }
    public void setDiscountPrice(double discountPrice) { this.discountPrice = discountPrice; }

    public boolean isIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}
