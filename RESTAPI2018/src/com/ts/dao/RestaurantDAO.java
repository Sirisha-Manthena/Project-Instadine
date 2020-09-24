package com.ts.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rest.dto.Customer;
import com.rest.dto.Department;
import com.rest.dto.Employee;
import com.rest.dto.Restaurant;
import com.ts.db.HibernateTemplate;

public class RestaurantDAO {
private static SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	public int register(Restaurant dept) {		
		return HibernateTemplate.addObject(dept);
	}
	public List<Restaurant> getAllRestaurant() {
	List<Restaurant> restaurants = (List)HibernateTemplate.getObjectListByQuery("From Restaurant");
		System.out.println("Inside All Restaurants ..."+restaurants);
		return restaurants;	
	}
	public Restaurant getRestaurantById(int restaurantId) {
		return (Restaurant)HibernateTemplate.getObject(Restaurant.class,restaurantId);
	}
	public Restaurant getRestaurantByEmail(String email) {
		return (Restaurant)HibernateTemplate.getObject(Restaurant.class, email);
	}
	public List<Restaurant> getRestaurantByLocation(String location) {
		List<Restaurant> restaurants = (List)HibernateTemplate.getObjectListByName(Restaurant.class, "location", location);
		return restaurants;
	}
	public List<Restaurant> getRestaurantByCategory(String category) {
		List<Restaurant> restaurants = (List)HibernateTemplate.getObjectListByName(Restaurant.class, "category", category);
		return restaurants;
	}
	public List<Restaurant> getAllVerifiedRestaurant() {
		
		List<Restaurant> restaurants = (List)HibernateTemplate.getObjectListByName(Restaurant.class, "status", "verified");
		return restaurants;
	}
	public List<Restaurant> getRestaurantByPrice(double price) {
		List<Restaurant> restaurants = (List)HibernateTemplate.getObjectListByName(Restaurant.class, "price", price);
		return restaurants;
	}
	public int deleteRestaurant(int restaurantId){
		return HibernateTemplate.deleteObject(Restaurant.class,restaurantId);
	}
	public int update(Restaurant restaurant){
		return HibernateTemplate.updateObject(restaurant);
	
	}
	public static Restaurant getRestaurantByEmailPassword(String email,String password) {
		
		String queryString = "from Restaurant where email = :email and password =:password";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("email", email);
		  query.setString("password", password);
		  Object queryResult = query.uniqueResult();
		  Restaurant restaurant = (Restaurant)queryResult;
		  return restaurant; 
		}
	
	public List<Restaurant> search(String restaurantName){
		List<Restaurant> restaurants = (List)HibernateTemplate.getObjectListByName(Restaurant.class, "restaurantName", restaurantName);
		return restaurants;
	}
}


