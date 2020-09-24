package com.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//Regarding Food Registration ignore it
@JsonIgnoreProperties("orderDetails")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "foodId")

@XmlRootElement
@Entity
public class Food {
	@Id@GeneratedValue
	private int foodId;
	private double price;
	private String foodName;
	private String category;
	private String imageName;
	
	@ManyToOne
	@JoinColumn(name = "restaurantId")
	@JsonBackReference(value="food-res")
	private Restaurant restaurant;
	
	@JsonBackReference(value="detail-food")
	@OneToMany(mappedBy="food")
	private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
