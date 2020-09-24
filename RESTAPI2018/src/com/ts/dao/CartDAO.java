package com.ts.dao;

import com.rest.dto.Admin;
import com.rest.dto.Cart;
import com.ts.db.HibernateTemplate;

public class CartDAO {
	public int register(Cart cart) {		
		return HibernateTemplate.addObject(cart);
		}
}
