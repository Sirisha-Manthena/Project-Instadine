package com.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.dto.BookingDetails;
import com.rest.dto.CartItem;
import com.rest.dto.Customer;
import com.rest.dto.Food;
import com.rest.dto.OrderDetails;
import com.rest.dto.Orders;
import com.rest.dto.Rating;
import com.rest.dto.Restaurant;
import com.rest.dto.Table_book;
import com.ts.dao.CustomerDAO;
import com.ts.dao.FoodDAO;
import com.ts.dao.OrderDAO;
import com.ts.dao.OrderDetailsDAO;
import com.ts.dao.RatingDAO;
import com.ts.dao.RestaurantDAO;
import com.ts.dao.TableDAO;

@Path("myresource")
public class MyResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}
	// Use This myresource for your project building
	@Path("registerRestaurant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerEmp(Restaurant restaurant){
		System.out.println("Data recieved in register:"+restaurant);


		RestaurantDAO restaurantDAO = new RestaurantDAO();
		restaurantDAO.register(restaurant);
	}
	@Path("registerCustomer")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerEmp(Customer customer){
		System.out.println("Data recieved in register:"+customer);
		CustomerDAO customerDAOH = new CustomerDAO();
		customerDAOH.register(customer);
	}




	@Path("getRestaurantByEmailPassword/{email}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Restaurant getRestaurantByEmailPassword(@PathParam("email") String email, @PathParam("password") String password){
		RestaurantDAO restaurantDAO = new RestaurantDAO();
		Restaurant restaurant = restaurantDAO.getRestaurantByEmailPassword(email, password);
		return restaurant;

	}
	@Path("getCustomerByEmailPassword/{email}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerByEmailPassword(@PathParam("email") String email, @PathParam("password") String password){
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.getCustomerByEmailPassword(email, password);
		return customer;

	}

	@Path("getCustomerByEmail/{email}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerByEmailPassword(@PathParam("email") String email){
		System.out.println("recieved");
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.getCustomerByEmail(email);
		System.out.println(customer);
		return customer;


	}







	@Path("getFoodByRestaurantId/{restaurantId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Food> getFoodByRestaurantId(@PathParam("restaurantId") int restaurantId){
		FoodDAO foodDAO = new FoodDAO();			
		List<Food> foodList = foodDAO.getFoodByRestaurantId(restaurantId);
		return foodList;
	}
	@Path("getOrdersByRestaurantId/{restaurantId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Orders> getOrdersByRestaurantId(@PathParam("restaurantId") int restaurantId){
		OrderDAO orderDAO = new OrderDAO();			
		List<Orders> orderList = orderDAO.getOrdersByRestaurantId(restaurantId);
		return orderList;
	}

	@Path("getOrderDetailsByOrderId/{orderId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderDetails> getOrderDetailsByOrderId(@PathParam("orderId") int orderId){
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();			
		List<OrderDetails> orderList = orderDetailsDAO.getOrderDetailsByOrderId(orderId);
		return orderList;
	}

	@Path("getOrdersByCustomerId/{customerId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Orders> getOrdersByCustomerId(@PathParam("customerId") int customerId){
		OrderDAO orderDAO = new OrderDAO();
		System.out.println("Inside customer resource : "+customerId); 
		List<Orders> orderList = orderDAO.getOrdersByCustomerId(customerId);
		System.out.println(orderList);
		return orderList;
	}
	@Path("getTablesByRestaurantId/{restaurantId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Table_book> getTablesByRestaurantId(@PathParam("restaurantId") int restaurantId){
		TableDAO orderDAO = new TableDAO();			
		List<Table_book> orderList = orderDAO.getTablesByRestaurantId(restaurantId);
		return orderList;
	}
	@Path("getRestaurantById/{restaurantId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Restaurant getRestaurantById(@PathParam("restaurantId") int restaurantId){
		RestaurantDAO customerDAO = new RestaurantDAO();
		Restaurant restaurant = customerDAO.getRestaurantById(restaurantId);
		return restaurant;
	}

	@POST
	@Path("/uploadImage")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public void uploadImage(@FormDataParam("Image")InputStream fileInputStream,@FormDataParam("Image") FormDataContentDisposition
			formDataContentDisposition, @FormDataParam("foodName")String foodName,@FormDataParam("category")String category,@FormDataParam("price")float price,@FormDataParam("restaurant") String restaurantDetails) throws IOException{
		ObjectMapper objMapper = new ObjectMapper();
		Restaurant restaurant = objMapper.readValue(restaurantDetails,Restaurant.class);
		int read =0;
		byte[] bytes = new byte[1024];

		String path = this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[]=path.split("/WEB-INF/classes/");

		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+"/image/",formDataContentDisposition.getFileName()));

		while((read = fileInputStream.read(bytes)) != -1){
			out.write(bytes,0,read);
		}
		out.flush();
		out.close();

		Food food = new Food();
		food.setFoodName(foodName);
		food.setCategory(category);
		food.setPrice(price);
		food.setImageName(formDataContentDisposition.getFileName());
		food.setRestaurant(restaurant);
		FoodDAO foodDao = new FoodDAO();
		foodDao.register(food);
	}
	@Path("getAllRestaurants")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Restaurant> getAllRestaurants() { 
		RestaurantDAO empDAOH = new RestaurantDAO();			
		List<Restaurant> empList = empDAOH.getAllRestaurant();
		return empList;
	}


	@Path("getAllVerifiedRestaurants")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Restaurant> getAllVerifiedRestaurants() { 
		RestaurantDAO empDAOH = new RestaurantDAO();			
		List<Restaurant> empList = empDAOH.getAllVerifiedRestaurant();
		System.out.println(empList);
		return empList;
	}

	@Path("getTableByRestaurantIdTime/{restaurantId}/{bookingTime}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Table_book getTableByRestaurantIdTime(@PathParam("restaurantId") int restaurantId, @PathParam("bookingTime") String bookingTime){
		TableDAO customerDAO = new TableDAO();
		Table_book table = customerDAO.getTableByRestaurantIdTime(restaurantId,bookingTime);
		return table;
	}
	@Path("updateTable")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateTable(Table_book table){
		System.out.println("Data Recieved in edit:"+table);
		TableDAO empDAOH = new TableDAO();
		empDAOH.updateTable(table);
	}

	@Path("deleteFood/{foodId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteFood(@PathParam("foodId") int foodId) {
		System.out.println("Data Recieved in delete:" + foodId);
		FoodDAO foodDAO = new FoodDAO();
		foodDAO.deleteFood(foodId);


	}
	@Path("deleteTable/{tableId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteTable(@PathParam("tableId") int tableId) {
		System.out.println("Data Recieved in delete:" + tableId);
		TableDAO tableDAO = new TableDAO();
		TableDAO.deleteTable(tableId);


	}
	@Path("updateFood")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateFood(Food food){
		System.out.println("Data Recieved in edit:"+food);
		FoodDAO foodDAO = new FoodDAO();
		foodDAO.updateFood(food);
	}
	@Path("registerTable")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)

	public String registerTable(Table_book table_book) {
		Table_book dept = new Table_book();
		TableDAO deptDao = new TableDAO();
		deptDao.registerTable(table_book);
		return "Success Table table";
	}
	@POST
	@Path("/uploadImagerst")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public void uploadImagerst(@FormDataParam("Image")InputStream fileInputStream,@FormDataParam("Image") FormDataContentDisposition
			formDataContentDisposition,@FormDataParam("restaurantName")String restaurantName,@FormDataParam("category")String category,@FormDataParam("estPrice")float estPrice,@FormDataParam("email")String email,@FormDataParam("password")String password,@FormDataParam("status")String status,
			@FormDataParam("workingHours")String workingHours,@FormDataParam("description")String description,@FormDataParam("area")String area,@FormDataParam("landmark")String landmark, @FormDataParam("street")String street ,@FormDataParam("apt")String apt ,@FormDataParam("pin")int pin) throws IOException{

		int read =0;
		byte[] bytes = new byte[1024];
		
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[]=path.split("/WEB-INF/classes/");
		System.out.println(pathArr[0]);
		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+"/image/",formDataContentDisposition.getFileName()));

		while((read = fileInputStream.read(bytes)) != -1){
			out.write(bytes,0,read);
		}
		out.flush();
		out.close();

		Restaurant rst = new Restaurant();
		rst.setCategory(category);
		rst.setRestaurantName(restaurantName);
		rst.setEmail(email);
		rst.setPassword(password);
		rst.setEstPrice(estPrice);
		rst.setArea(area);
		rst.setStatus(status);
		rst.setLandmark(landmark);
		rst.setPin(pin);
		rst.setStreet(street);
		rst.setApt(apt);
		rst.setDescription(description);
		rst.setWorkingHours(workingHours);
		rst.setRestaurantImage(formDataContentDisposition.getFileName());
		RestaurantDAO restDao = new RestaurantDAO();
		restDao.register(rst);
	}



	@POST
	@Path("confirmOrders")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public  void  confirmOrders(@FormDataParam("restaurantId") int restaurantId,@FormDataParam("customerId") int customerId,@FormDataParam("items") String str,@FormDataParam("orderType")String orderType,@FormDataParam("amount") double amount,@FormDataParam("bookDetails")String str4) throws MessagingException{
		Orders orde=null;
		try {
			LocalDate dtf = LocalDate.now();
			LocalTime now = LocalTime.now();
			ObjectMapper objMapper = new ObjectMapper();
			BookingDetails bDetails = objMapper.readValue(str4,BookingDetails.class);

			ObjectMapper Mapper = new ObjectMapper();

			orde = new Orders();
			orde.setAmount(amount);
			orde.setOrderType(orderType);
			orde.setTime(Time.valueOf(now));
			orde.setDate(Date.valueOf(dtf));
			orde.setReserveDate(Date.valueOf(bDetails.getDate()));
			orde.setReserveTime(Time.valueOf(bDetails.getTime())); 
			orde.setGuests(bDetails.getGuests());
			orde.setAdds(bDetails.getAdds());
			Restaurant restaurant = new Restaurant();
			restaurant.setRestaurantId(restaurantId); 
			orde.setRestaurant(restaurant);
			Customer customer = new Customer();
			customer.setCustomerId(customerId); 
			orde.setCustomer(customer);
			OrderDAO dao = new OrderDAO();
			dao.register(orde);

			List<OrderDetails> ordDetails = new ArrayList<OrderDetails>();
			ObjectMapper obj = new ObjectMapper();
			JSONParser json = new JSONParser();
			JSONArray  arr=(JSONArray)json.parse(str);
			System.out.println("Inside order detils..."+arr.size());
			for (int i=0;i<arr.size();i++){
				JSONObject jsonData=(JSONObject)arr.get(i);
				CartItem cartItem = obj.readValue(jsonData.toString(),CartItem.class);
				OrderDetails orderDetails = new OrderDetails();
				Food food = new Food();
				food.setFoodId(cartItem.getFoodId());
				orderDetails.setFood(food);
				orderDetails.setOrders(orde);
				orderDetails.setQuantity(cartItem.getQuantity());
				orderDetails.setTotalPrice(cartItem.getTotalPrice());
				OrderDetailsDAO or = new OrderDetailsDAO();
				or.register(orderDetails);
			}

		String subject = "New "+orde.getOrderType()+" Confirmation";
		String body= "Hey "+orde.getRestaurant().getRestaurantName() + "!!!\n"
				+"You have a "+orde.getOrderType() + " order \n"
				+"OrderId: "+orde.getOrderId() + " \n"
				+"Customer Name: "+ orde.getCustomer().getFullName()+" "+"\n"
				+"Amount:"+orde.getAmount()+"\n"
				+"Go to  View Orders in your profile for further Information";
				
				
				
		String email= orde.getRestaurant().getEmail();
		String host = "smtp.gmail.com";
		String from = "instadine999@gmail.com";
		String pass = "instadine#4";

		Properties props = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		String[] to = {email};

		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));

		InternetAddress[] toAddress = new InternetAddress[to.length];

		for( int i=0; i < to.length; i++){
			toAddress[i] = new InternetAddress(to[i]);
		}

		for( int i=0; i < toAddress.length; i++){
			message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		}

		message.setSubject(subject);
		message.setText(body);

		Transport transport = session.getTransport("smtp");

		transport.connect(host, from, pass);
		transport.sendMessage(message, message.getAllRecipients());

		transport.close();
		System.out.println("Successful");

		String subject2 = "Order Confirmation Email";
		String body2= "Hey "+orde.getCustomer().getFullName()+" !!!\n"
				+"You have orderd a "+orde.getOrderType() + " \n"
				+"OrderId: "+orde.getOrderId() + " \n"
				+"Restaurant Name: "+ orde.getRestaurant().getRestaurantName()+"\n"
				+"Amount:"+orde.getAmount()+"\n"
				+"Go to  View Orders in your profile for further Information";

		String email2= orde.getCustomer().getEmail();
		/*String host = "smtp.gmail.com";
		String from = "instadine999@gmail.com";
		String pass = "instadine#4";

		Properties props2 = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");*/

		String[] to2 = {email2};

		Session session2 = Session.getDefaultInstance(props, null);
		MimeMessage message2 = new MimeMessage(session2);
		message2.setFrom(new InternetAddress(from));

		InternetAddress[] toAddress2 = new InternetAddress[to.length];

		for( int i=0; i < to.length; i++){
			toAddress2[i] = new InternetAddress(to2[i]);
		}

		for( int i=0; i < toAddress.length; i++){
			message2.addRecipient(Message.RecipientType.TO, toAddress2[i]);
		}

		message2.setSubject(subject2);
		message2.setText(body2);

		//Transport transport = session.getTransport("smtp");

		transport.connect(host, from, pass);
		transport.sendMessage(message2, message2.getAllRecipients());

		transport.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Customer email sent");

	}




	/*@Path("mail")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String hello(mail mailData) throws MessagingException {
		System.out.println(mailData);

		String subject = mailData.getOrderType();
		String body="OrderType:"+mailData.getOrderType()+"\nBookingDate:"+mailData.getDate()+"\nBookingTime:"+mailData.getTime()+"\nNo.of Guests:"+mailData.getGuests()+"\nAdditional Details:"+mailData.getAdds();
		String email= mailData.getRestaurantEmail();
		String host = "smtp.gmail.com";
		String from = "instadine999@gmail.com";
		String pass = "instadine#4";

		Properties props = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		String[] to = {email};

		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));

		InternetAddress[] toAddress = new InternetAddress[to.length];

		for( int i=0; i < to.length; i++){
			toAddress[i] = new InternetAddress(to[i]);
		}

		for( int i=0; i < toAddress.length; i++){
			message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		}

		message.setSubject(subject);
		message.setText(body);

		Transport transport = session.getTransport("smtp");

		transport.connect(host, from, pass);
		transport.sendMessage(message, message.getAllRecipients());

		transport.close();
		return "Successful";
	}*/

	@Path("updateRst")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateRst(Restaurant restaurant) throws AddressException, MessagingException{
		System.out.println("Data Recieved in edit:"+restaurant);
		RestaurantDAO rst= new RestaurantDAO();
		rst.update(restaurant);

		String subject ="Confirmation";
		String body="You can now login to your account and add your menu and all";
		String email= restaurant.getEmail();
		String host = "smtp.gmail.com";
		String from = "instadine999@gmail.com";
		String pass = "instadine#4";

		Properties props = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		String[] to = {email};

		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));

		InternetAddress[] toAddress = new InternetAddress[to.length];

		for( int i=0; i < to.length; i++){
			toAddress[i] = new InternetAddress(to[i]);
		}

		for( int i=0; i < toAddress.length; i++){
			message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		}

		message.setSubject(subject);
		message.setText(body);

		Transport transport = session.getTransport("smtp");

		transport.connect(host, from, pass);
		transport.sendMessage(message, message.getAllRecipients());

		transport.close();
	}
	@Path("deleteRst/{restaurantId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteRst(@PathParam("restaurantId") int restaurantId) {
		System.out.println("Data Recieved in delete:" + restaurantId);
		RestaurantDAO tableDAO = new RestaurantDAO();
		tableDAO.deleteRestaurant(restaurantId);


	}



	@Path("updateOrder")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateOrder(Orders orderUpdate){
		System.out.println("Data Recieved in edit:"+orderUpdate);
		OrderDAO empDAOH = new OrderDAO();
		empDAOH.updateOrder(orderUpdate);
	}
	@Path("registerRating")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerRating(Rating rating){
		System.out.println("Data recieved in register:"+rating);
		RatingDAO customerDAOH = new RatingDAO();
		customerDAOH.register(rating);
	}

	@Path("updateRating")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateRating(Rating rating){
		System.out.println("Data Recieved in edit:"+rating);
		RatingDAO empDAOH = new RatingDAO();
		empDAOH.updateRating(rating);
	}
	@Path("getRating/{restaurantId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Rating getRating(@PathParam("restaurantId") int restaurantId){
		RatingDAO customerDAO = new RatingDAO();
		Rating rating1 = customerDAO.getRating(restaurantId);
		System.out.println("this rating in get"+rating1);
		return rating1;
	}
}





