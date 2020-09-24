package com.ts.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rest.dto.Customer;
import com.rest.dto.Employee;
import com.rest.dto.Restaurant;
import com.ts.db.HibernateTemplate;

public class CustomerDAO {

private static SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	public int register(Customer customer) {	
		System.out.println("Testing customer reg..DAO");
		return HibernateTemplate.addObject(customer);
		}
	public int update(Customer customer){
		return HibernateTemplate.updateObject(customer);
	}
	public int deleteCustomer(int customerId){
		return HibernateTemplate.deleteObject(Customer.class,customerId);
	}
	public List<Customer> getAllCustomers() {
		List<Customer> customers = (List)HibernateTemplate.getObjectListByQuery("From Customer");
		System.out.println("Inside All Restaurants ..."+customers);
		return customers;	
	}
public static Customer getCustomerByEmailPassword(String email,String password) {
		
		String queryString = "from Customer where email = :email and password =:password";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("email", email);
		  query.setString("password", password);
		  Object queryResult = query.uniqueResult();
		  Customer customer = (Customer)queryResult;
		  return customer; 
		}
public Customer getCustomerByEmail(String email) {
	String queryString = "from Customer where email = :email ";
	  Query query = sessionFactory.openSession().createQuery(queryString);
	  query.setString("email", email);
	  
	  Object queryResult = query.uniqueResult();
	  Customer customer = (Customer)queryResult;
	  return customer; 
}

}
