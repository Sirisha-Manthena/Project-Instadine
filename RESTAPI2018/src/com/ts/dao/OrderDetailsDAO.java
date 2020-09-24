package com.ts.dao;


import java.util.List;

import com.rest.dto.OrderDetails;

import com.ts.db.HibernateTemplate;

public class OrderDetailsDAO {
	public int register(OrderDetails orderdetails) {
		System.out.println("Inside Order Details Dao...");
		return HibernateTemplate.addObject(orderdetails);
		}
	public List<OrderDetails> getOrderDetailsByOrderId(int orderId ) {	
		return (List)HibernateTemplate.getObjectListByName(OrderDetails.class,"orders.orderId",orderId);
	}
}
