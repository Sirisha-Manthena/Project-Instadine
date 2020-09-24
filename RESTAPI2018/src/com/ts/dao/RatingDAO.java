package com.ts.dao;



import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rest.dto.Department;
import com.rest.dto.Food;
import com.rest.dto.Orders;
import com.rest.dto.Rating;
import com.ts.db.HibernateTemplate;

public class RatingDAO {
private static SessionFactory sessionFactory;

static {
sessionFactory = new Configuration().configure().buildSessionFactory();
}

public int register(Rating rating) {
return HibernateTemplate.addObject(rating);
}
public int updateRating(Rating rating) {
return HibernateTemplate.updateObject(rating);

}
public Rating getRating(int restaurantId){
int rating = 0;
String queryString = "from Rating where restaurantId = :restaurantId";
 Query query = sessionFactory.openSession().createQuery(queryString);
 query.setInteger("restaurantId", restaurantId);
 Object queryResult = query.uniqueResult();
 Rating orders = (Rating)queryResult;
 return orders;
}

}

	
	
	
