package com.ts.dao;

import com.rest.dto.BookingDetails;
import com.ts.db.HibernateTemplate;

public class BookingDetailsDAO {
	public int register(BookingDetails bookingDetails) {		
		return HibernateTemplate.addObject(bookingDetails);
		}

}
