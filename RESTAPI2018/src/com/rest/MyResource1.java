package com.rest;

import java.sql.Time;
import java.sql.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.dto.Admin;

import com.rest.dto.Customer;
import com.rest.dto.Department;
import com.rest.dto.Employee;
import com.rest.dto.Food;
import com.rest.dto.OrderDetails;
import com.rest.dto.Orders;
import com.rest.dto.Restaurant;
import com.rest.dto.Table_book;
import com.ts.dao.AdminDAO;

import com.ts.dao.CustomerDAO;
import com.ts.dao.DeptDAO;
import com.ts.dao.EmployeeDAO;
import com.ts.dao.FoodDAO;
import com.ts.dao.OrderDAO;
import com.ts.dao.OrderDetailsDAO;
import com.ts.dao.RestaurantDAO;
import com.ts.dao.TableDAO;

@Path("myresource1")
public class MyResource1 {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    @Path("restTest")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String restTest() {
    	
    	Food food = new Food();
    	food.setCategory("Non-veg");
    	food.setFoodId(1);
    	food.setFoodName("Manchuria");
    	food.setPrice(100.90);
    	FoodDAO foodDao = new FoodDAO();
    	foodDao.register(food);
    	
    	Restaurant dept = new Restaurant();
    	dept.setRestaurantId(1);
    	dept.setRestaurantName("ACCOUNTS");
    	dept.setCategory("Non-veg");
    	dept.setEmail("123@456.com");
    	dept.setEstPrice(12.90);
    	dept.setPassword("password");
    	dept.setStatus("pending");
    	//dept.setLocation("HYDERABAD");
    	//dept.getFoodList().add(food);
    	
    	dept.setDescription("Yeah");
    	dept.setWorkingHours("12pm to 3pm");
    	RestaurantDAO deptDao = new RestaurantDAO();
    	deptDao.register(dept);
    	
    	return "Success Restaurant table";
    }
    @Path("getAllRestaurants")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Restaurant> getAllRestaurants() {
	
	RestaurantDAO empDAOH = new RestaurantDAO();			
	List<Restaurant> empList = empDAOH.getAllRestaurant();
	return empList;
    }
    @Path("foodTest")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String foodTest() {
    	Food dept = new Food();
    	dept.setCategory("Non-veg");
    	dept.setFoodId(1);
    	dept.setFoodName("Manchuria");
    	dept.setPrice(100.90);
    	FoodDAO deptDao = new FoodDAO();
    	deptDao.register(dept);
    	
    	return "Success Food table";
    }
    @Path("tableTest")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String tableTest() {
    	Table_book dept = new Table_book();
    	dept.setAvailability(3);
    	dept.setBookingTime(Time.valueOf("1:23:40"));
    	TableDAO deptDao = new TableDAO();
    	deptDao.registerTable(dept);
    	
    	return "Success Table table";
    }
    @Path("AdminTest")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String adminTest() {
    	Admin dept = new Admin();
    	dept.setAdminId(1);
    	dept.setPassword("password");
    	dept.setUsername("Admin");
    	AdminDAO deptDao = new AdminDAO();
    	deptDao.register(dept);
    	return "Success Admin table";
    }
  
    	
   /* @Path("OrderTest")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String orderTest() {
    	
    	Orders dept = new Orders();
    	dept.setAmount(123.90);
    	dept.setOrderId(1);
    	dept.setOrderType("Table");
    	dept.setDate(Date.valueOf("1973-03-25"));
    	dept.setTime(Time.valueOf("1:23:40"));
    	
    	OrderDAO orderdao = new OrderDAO();
    	orderdao.register(dept);
    	return "Success Food table";
    }*/
   
    @Path("getAllOrder")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getAllOrders() {
	
	OrderDAO empDAOH = new OrderDAO();			
	List<Orders> empList = empDAOH.getAllOrders();
	return empList;
    }
    @Path("getAllCustomers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers() {
	
	CustomerDAO empDAOH = new CustomerDAO();			
	List<Customer> empList = empDAOH.getAllCustomers();
	return empList;
    }
   
}
