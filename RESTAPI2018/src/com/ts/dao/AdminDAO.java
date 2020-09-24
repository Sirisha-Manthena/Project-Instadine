package com.ts.dao;

import java.util.List;

import com.rest.dto.Admin;
import com.rest.dto.Restaurant;
import com.ts.db.HibernateTemplate;

public class AdminDAO {
	public int register(Admin admin) {		
		return HibernateTemplate.addObject(admin);
	}
	public List<Admin> getAllRestaurant() {
		List<Admin> admins = (List)HibernateTemplate.getObjectListByQuery("From Admin");
		System.out.println("Inside All Admin ..."+admins);
		return admins;	
	}

}
