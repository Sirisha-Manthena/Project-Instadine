package com.ts.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rest.dto.Admin;
import com.rest.dto.Employee;
import com.rest.dto.Food;
import com.rest.dto.Table_book;
import com.ts.db.HibernateTemplate;

public class TableDAO {
private static SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	public int registerTable(Table_book table) {		
		return HibernateTemplate.addObject(table);
	}
	public List<Table_book> getTablesByRestaurantId(int restaurantId) {	
		return (List)HibernateTemplate.getObjectListByName(Table_book.class,"restaurant.restaurantId",restaurantId);
	}
	public Table_book getTableByRestaurantIdTime(int restaurantId, String bookingTime){
		String queryString = "from Table_book where restaurantId = :restaurantId and bookingTime =:bookingTime";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setInteger("restaurantId", restaurantId);
		  query.setString("bookingTime", bookingTime);
		  Object queryResult = query.uniqueResult();
		  Table_book table = (Table_book)queryResult;
		  return table; 
	}
	public int updateTable(Table_book table) {		
		return HibernateTemplate.updateObject(table);
	}
	public static void deleteTable(int tableId) {
		HibernateTemplate.deleteObject(Table_book.class, tableId);
	}

}
