package com.rest.dto;


	import javax.persistence.Entity;
	import javax.persistence.FetchType;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.OneToOne;
	import javax.xml.bind.annotation.XmlRootElement;
	@XmlRootElement
	@Entity
	public class Rating {
	@Id@GeneratedValue
	private int ratingId;
	private int sum;
	private int count;
	private double average;

	@OneToOne
	@JoinColumn(name = "restaurantId")
	private Restaurant restaurant;

	public Rating(int ratingId, int sum, int count, double average, Restaurant restaurant) {
	super();
	this.ratingId = ratingId;
	this.sum = sum;
	this.count = count;
	this.average = average;
	this.restaurant = restaurant;
	}

	public Rating() {
	super();
	// TODO Auto-generated constructor stub
	}

	public int getRatingId() {
	return ratingId;
	}

	public void setRatingId(int ratingId) {
	this.ratingId = ratingId;
	}

	public int getSum() {
	return sum;
	}

	public void setSum(int sum) {
	this.sum = sum;
	}

	public int getCount() {
	return count;
	}

	public void setCount(int count) {
	this.count = count;
	}

	public double getAverage() {
	return average;
	}

	public void setAverage(double average) {
	this.average = average;
	}

	public Restaurant getRestaurant() {
	return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
	this.restaurant = restaurant;
	}

	
	


	}

		
		
		



