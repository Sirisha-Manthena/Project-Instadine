package com.ts.dao;

import java.util.List;

import com.rest.dto.Department;
import com.rest.dto.Food;
import com.rest.dto.Orders;
import com.ts.db.HibernateTemplate;

public class OrderDAO {
	public int register(Orders orders) {		
		return HibernateTemplate.addObject(orders);
	}
	public List<Orders> getAllOrders() {
		List<Orders> orders=(List)HibernateTemplate.getObjectListByQuery("From Orders");
		return orders;	
	}
	public List<Orders> getOrdersByRestaurantId(int restaurantId) {	
		return (List)HibernateTemplate.getObjectListByName(Orders.class,"restaurant.restaurantId",restaurantId);
	}
	

	public List<Orders> getOrdersByCustomerId(int customerId) {	
		return (List)HibernateTemplate.getObjectListByName(Orders.class,"customer.customerId",customerId);
	}
	public int updateOrder(Orders orderUpdate) { 
		return HibernateTemplate.updateObject(orderUpdate);
		
	}
	
//	public List<Orders> getOrdersByCustomerId(int customerId) {	
//		String query= "from Orders where customerId = :customerId";
//		  List<Orders> obj = (List<Orders>) HibernateTemplate.getObjectListByQuery(query);
//		  
//		  System.out.println(obj); 
//		  
//		  return obj;
//		}

}
