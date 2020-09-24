package com.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Embeddable
public class Cart {
	
	private int cartId;
	private int quantity;
	private int foodId;
	private String foodName;
	private double price;
	
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Cart(int cartId, int quantity, int foodId, String foodName, double price) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
		//this.customerId = customerId;
		this.foodId = foodId;
		this.foodName = foodName;
		this.price = price;
	}


	public int getCartId() {
		return cartId;
	}


	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getFoodId() {
		return foodId;
	}


	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}


	public String getFoodName() {
		return foodName;
	}


	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
	
	
	
	
	
	
	


