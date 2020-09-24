package com.ts.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rest.dto.Department;
import com.rest.dto.Food;
import com.rest.dto.Restaurant;
import com.rest.dto.Table_book;
import com.ts.db.HibernateTemplate;

public class FoodDAO {
private static SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	public int register(Food food) {		
		return HibernateTemplate.addObject(food);
	}
		
	public List<Food> getFoodByRestaurantId(int restaurantId) {	
		return (List)HibernateTemplate.getObjectListByName(Food.class,"restaurant.restaurantId",restaurantId);
	}
	public static void deleteFood(int foodId) {
		HibernateTemplate.deleteObject(Food.class, foodId);
	}

	public int updateFood(Food food) {
		return HibernateTemplate.updateObject(food);
		
	}
	public Food getFoodByFoodName(int restaurantId, String foodName){
		String queryString = "from Food where restaurantId = :restaurantId and foodName =:foodName";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setInteger("restaurantId", restaurantId);
		  query.setString("foodName", foodName);
		  Object queryResult = query.uniqueResult();
		  Food food = (Food)queryResult;
		  return food; 
	}
}
