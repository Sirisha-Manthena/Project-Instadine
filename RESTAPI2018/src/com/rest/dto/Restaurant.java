package com.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//Regarding Restaurant Registration ignore it
@JsonIgnoreProperties("orderList")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "restaurantId")
@Entity
@XmlRootElement
public class Restaurant {
	@Id@GeneratedValue
	private int restaurantId;
	private String area;
	private String landmark;
	private String street;
	private int pin;
	private String apt;
	private String restaurantName;
	private String email;
	private String password;
	private String category;
	private double estPrice;
	private String status;
	private String description;
	private String workingHours;
	private String restaurantImage;
	
	@JsonManagedReference(value="food-res")
	@OneToMany(mappedBy="restaurant")
	private List<Food> foodList = new ArrayList<Food>();
	@JsonManagedReference(value="tab-res")
	@OneToMany(mappedBy="restaurant")
	private List<Table_book> tableList = new ArrayList<Table_book>();
	@JsonBackReference(value="ord-res")
	@OneToMany(mappedBy="restaurant")
	private List<Orders> orderList = new ArrayList<Orders>();
		
	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getApt() {
		return apt;
	}

	public void setApt(String apt) {
		this.apt = apt;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getEstPrice() {
		return estPrice;
	}

	public void setEstPrice(double estPrice) {
		this.estPrice = estPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getRestaurantImage() {
		return restaurantImage;
	}

	public void setRestaurantImage(String restaurantImage) {
		this.restaurantImage = restaurantImage;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}

	public List<Table_book> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table_book> tableList) {
		this.tableList = tableList;
	}

	public List<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}

}


