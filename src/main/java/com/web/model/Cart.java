package com.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // If user is logged-in, this is set. If guest, it is null
    @ManyToOne
    private User user;

    @ManyToOne
    private Food food;

    private Integer quantity;

    // Not stored in DB, calculated on the fly
    @Transient
    private Double totalPrice;

    // Not stored in DB, sum of all items for display
    @Transient
    private Double totalOrderPrice;
    
    public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public Cart(Integer id, User user, Food food, Integer quantity, Double totalPrice, Double totalOrderPrice) {
		super();
		this.id = id;
		this.user = user;
		this.food = food;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.totalOrderPrice = totalOrderPrice;
	}

	// ====== Getters & Setters ======
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Food getFood() { return food; }
    public void setFood(Food food) { this.food = food; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public Double getTotalOrderPrice() { return totalOrderPrice; }
    public void setTotalOrderPrice(Double totalOrderPrice) { this.totalOrderPrice = totalOrderPrice; }

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", food=" + food + ", quantity=" + quantity + ", totalPrice="
				+ totalPrice + ", totalOrderPrice=" + totalOrderPrice + "]";
	}
    
}
